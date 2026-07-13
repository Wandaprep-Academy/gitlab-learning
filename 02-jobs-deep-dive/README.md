# 02 — Jobs Deep Dive: build, test, deploy

## Job Anatomy

Every job in `.gitlab-ci.yml` can have:

| Key            | Purpose                                           | Required? |
|----------------|---------------------------------------------------|-----------|
| `stage:`       | Which stage this job belongs to                   | Yes       |
| `image:`       | Docker image to run in (overrides default)        | No        |
| `script:`      | The commands to execute — this is the job's work  | Yes       |
| `before_script:` | Commands run before `script:` — typically setup | No        |
| `after_script:` | Always runs even if `script:` fails — cleanup   | No        |
| `rules:`       | Conditions that control when this job runs        | No        |
| `needs:`       | Skip stage ordering — run as soon as dependencies are ready | No |
| `allow_failure:` | If true, job failure doesn't fail the pipeline | No        |

## The Build → Test → Deploy Pattern

```
Push to branch
      |
      v
  [build]          Compile / package code
      |
      v
  [test-unit]      Fast unit tests
  [test-integration] Slower integration tests (parallel with unit)
  [test-lint]      Code style checks (parallel)
      |
      v
  [deploy-staging] Auto-deploy to staging (main branch only)
      |
      v
  [deploy-prod]    Manual gate — a human must click to deploy
```
