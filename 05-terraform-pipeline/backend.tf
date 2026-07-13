# backend.tf
# S3 backend with DynamoDB locking.
# The actual values come from CI/CD variables via -backend-config flags.
# This avoids hardcoding bucket names and regions in the code.

terraform {
  backend "s3" {
    # Values supplied at runtime via:
    # terraform init -backend-config="bucket=$TF_STATE_BUCKET"
    #                -backend-config="key=terraform.tfstate"
    #                -backend-config="region=$AWS_DEFAULT_REGION"
    #                -backend-config="dynamodb_table=$TF_LOCK_TABLE"
    #                -backend-config="encrypt=true"
    encrypt = true
  }
}
