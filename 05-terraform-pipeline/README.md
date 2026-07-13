# 05 — Terraform GitLab Pipeline

## What This Pipeline Does

A production-grade Terraform pipeline that:

1. Validates and formats Terraform code
2. Runs `terraform plan` and stores the plan as an artifact
3. Shows the plan summary in the Merge Request (using the `terraform` report type)
4. Requires a **manual approval** gate before `terraform apply`
5. Locks state using a DynamoDB table to prevent concurrent runs

## Required GitLab CI/CD Variables

Set these in: **Project → Settings → CI/CD → Variables**

| Variable              | Value                                    | Masked | Protected |
|-----------------------|------------------------------------------|--------|-----------|
| `AWS_ACCESS_KEY_ID`   | Your AWS access key                      | Yes    | Yes       |
| `AWS_SECRET_ACCESS_KEY` | Your AWS secret key                    | Yes    | Yes       |
| `AWS_DEFAULT_REGION`  | e.g. `eu-west-2`                         | No     | No        |
| `TF_STATE_BUCKET`     | S3 bucket name for Terraform state       | No     | Yes       |
| `TF_LOCK_TABLE`       | DynamoDB table name for state locking    | No     | Yes       |
| `TF_VAR_environment`  | `dev`, `staging`, or `prod`             | No     | No        |

## S3 Backend Setup (one-time, before first run)

```bash
# Create the S3 bucket for state storage
aws s3 mb s3://my-company-tf-state --region eu-west-2

# Enable versioning so you can roll back state
aws s3api put-bucket-versioning \
  --bucket my-company-tf-state \
  --versioning-configuration Status=Enabled

# Create the DynamoDB table for state locking
aws dynamodb create-table \
  --table-name terraform-state-lock \
  --attribute-definitions AttributeName=LockID,AttributeType=S \
  --key-schema AttributeName=LockID,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST \
  --region eu-west-2
```

## Pipeline Flow

```
Push to branch
     |
     v
[validate]    terraform validate + fmt --check
     |
     v
[plan]        terraform plan -out=tfplan
              → artifact: tfplan (binary)
              → artifact: plan.json (MR widget)
     |
     v
[apply]       terraform apply tfplan    <-- MANUAL gate on main only
```
