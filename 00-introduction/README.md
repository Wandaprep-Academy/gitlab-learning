# 00 — Introduction to GitLab

## What Is GitLab?

GitLab is a **complete DevOps platform** — a single application covering the
entire software development lifecycle: planning, source code management,
CI/CD pipelines, security scanning, infrastructure management, and monitoring.

> **Key distinction:** GitHub is source control with CI as an add-on.
> GitLab was designed from day one as a unified DevOps platform.

## GitLab vs GitHub vs Jenkins

| Feature               | GitLab          | GitHub       | Jenkins       |
|-----------------------|-----------------|--------------|---------------|
| Source control        | Yes             | Yes          | No            |
| Built-in CI/CD        | Yes (native)    | Actions      | Core purpose  |
| Container registry    | Yes (built-in)  | Yes          | Plugin        |
| Package registry      | Yes             | Yes          | Plugin        |
| Terraform state       | Yes (built-in)  | No           | No            |
| Self-hosted           | Yes (CE/EE)     | GHE          | Yes           |
| SaaS option           | gitlab.com      | github.com   | No            |
| Security scanning     | Yes (all tiers) | Advanced     | Plugin        |

## The Three Core Pillars: Code → Build → Deploy

### Code (Source Code Management)
- Git repositories with branch protection rules
- Merge Requests (MRs) with inline code review
- Code Owners for mandatory approval workflows
- Wiki, snippets, and integrated documentation

### Build (CI/CD Pipelines)
- Pipelines defined in `.gitlab-ci.yml` at repo root
- GitLab Runners execute each job in isolation
- Built-in container registry (`registry.gitlab.com/...`)
- Package registries: Maven, npm, PyPI, Helm, Conan
- SAST, DAST, dependency scanning, secret detection

### Deploy (Release & Operations)
- Environments: development / staging / production
- Deployment rollbacks and manual approval gates
- Review Apps: spin up a live environment per Merge Request
- Feature flags (integrated with Unleash)
- GitLab Agent for Kubernetes (push and pull deployments)

## GitLab Architecture

```
Developer pushes code
        |
        v
  +-----------+         +-----------------------+
  |  GitLab   |-------->|   Pipeline Scheduler  |
  |  Server   |         +-----------+-----------+
  +-----------+                     |
                                    | dispatches jobs
                    ________________|________________
                   |                |                |
                   v                v                v
           +-----------+    +-----------+    +-----------+
           |  Runner A  |    |  Runner B  |    |  Runner C  |
           | (shell)    |    | (docker)   |    | (k8s)      |
           +-----------+    +-----------+    +-----------+
```

## Key Terminology

| Term            | Meaning                                                      |
|-----------------|--------------------------------------------------------------|
| Pipeline        | The full set of stages and jobs triggered by a git event     |
| Stage           | A group of jobs that run in parallel at the same time        |
| Job             | A single unit of work (shell commands) run by a Runner       |
| Runner          | An agent that picks up and executes jobs                     |
| Artifact        | Files produced by a job, passed to later jobs or downloaded  |
| Environment     | A named deployment target (dev, staging, prod)               |
| Merge Request   | A proposal to merge one branch into another (with review)    |
