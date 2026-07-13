# 01 — Basic GitLab Pipeline

## What We Cover
- The `.gitlab-ci.yml` file format
- `stages:` — defining the execution order
- Jobs — the units of work
- GitLab Runners — what executes the jobs
- `image:` — which Docker image to use
- `script:` — the actual commands to run

## GitLab Runners

A Runner is an agent process that polls GitLab for available jobs and
executes them. Runners can run on:

| Executor     | Best For                                 | Notes                              |
|--------------|------------------------------------------|------------------------------------|
| Docker       | Most CI/CD work — isolated, reproducible | Requires Docker on the runner host |
| Shell        | Simple scripts, legacy pipelines         | Runs as the runner's OS user       |
| Kubernetes   | Large-scale, cloud-native CI/CD          | Spawns a pod per job               |
| Virtual Machine | Windows builds, GUI testing           | Full OS isolation                  |

### Runner Scope
- **Shared runners:** Provided by GitLab.com (or your admin). Available to all projects.
- **Group runners:** Registered to a GitLab group. Shared across all projects in the group.
- **Project runners:** Registered to one specific project only.

See the `.gitlab-ci.yml` file in this folder for the working pipeline example.
