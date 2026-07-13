# 07 — include: Composing Pipelines from Reusable Templates

## Why include: Matters

As your team grows, copy-pasting `.gitlab-ci.yml` blocks across projects becomes
painful and dangerous — a security fix or a changed runner tag has to be applied
to every project manually.

`include:` solves this by letting you pull in external YAML files and merge them
into your pipeline at runtime. The result is a single, authoritative source of
truth for common pipeline logic that every project simply includes.

## The Four include: Types

| Type | Syntax | Loads From | Best For |
|------|--------|-----------|---------|
| `local:` | `- local: path/to/file.yml` | Same repository | Project-specific shared templates |
| `project:` | `- project: group/repo` | Another GitLab repo | Organisation-wide shared templates |
| `remote:` | `- remote: https://...` | Any HTTPS URL | Third-party or public templates |
| `template:` | `- template: Jobs/Build.gitlab-ci.yml` | GitLab's built-in library | Official GitLab managed templates |

## Files in This Section

```
07-include/
  .gitlab/
    templates/
      build.yml          Local template — build job (used by root .gitlab-ci.yml)
      test.yml           Local template — test jobs
      deploy.yml         Local template — staging + production deploy jobs
  ci-templates/          Simulates a shared-templates repository
    build/
      docker.yml         Reusable Docker build + push job
      maven.yml          Reusable Maven compile + package job
    test/
      junit.yml          Reusable test + JUnit report job
      lint.yml           Reusable linting job
    deploy/
      ssh-deploy.yml     Base SSH deploy job (uses extends:)
    security/
      sast.yml           Reusable SAST scan job
    notify/
      slack.yml          On-failure Slack notification job
  .gitlab-ci.yml         Root pipeline — includes all templates above
```
