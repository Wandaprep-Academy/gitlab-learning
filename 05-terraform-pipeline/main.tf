# main.tf
# Example Terraform configuration — creates a simple S3 bucket.
# Replace this with your actual infrastructure.

terraform {
  required_version = ">= 1.6.0"
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = var.aws_region
}

variable "aws_region" {
  description = "AWS region to deploy resources into"
  type        = string
  default     = "eu-west-2"
}

variable "environment" {
  description = "Deployment environment (dev, staging, prod)"
  type        = string
}

resource "aws_s3_bucket" "example" {
  bucket = "my-app-assets-${var.environment}-${random_id.suffix.hex}"

  tags = {
    Environment = var.environment
    ManagedBy   = "Terraform"
    Pipeline    = "GitLab CI"
  }
}

resource "random_id" "suffix" {
  byte_length = 4
}

output "bucket_name" {
  value       = aws_s3_bucket.example.bucket
  description = "Name of the created S3 bucket"
}
