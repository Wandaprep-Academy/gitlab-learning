# 03 — GitLab CI/CD Variables

## Types of Variables

| Type                    | Where Set               | Scope                    | Use For                        |
|-------------------------|-------------------------|--------------------------|--------------------------------|
| Predefined CI variables | Built into GitLab       | All pipelines            | Commit SHA, branch, runner info |
| Project variables       | Project → Settings → CI/CD | That project's pipelines | Secrets for one project        |
| Group variables         | Group → Settings → CI/CD | All projects in group    | Shared secrets across a team   |
| Inline pipeline vars    | In `.gitlab-ci.yml`     | That pipeline only       | Non-sensitive config values    |
| Trigger variables       | Passed via API/trigger  | Triggered pipeline only  | Cross-project parameterisation |

## Most Useful Predefined Variables

| Variable                 | Contains                                       |
|--------------------------|------------------------------------------------|
| `$CI_PIPELINE_ID`        | Unique ID of the pipeline (e.g. 12345678)      |
| `$CI_JOB_ID`             | Unique ID of the current job                   |
| `$CI_COMMIT_SHA`         | Full 40-character commit hash                  |
| `$CI_COMMIT_SHORT_SHA`   | First 8 characters of the commit hash         |
| `$CI_COMMIT_REF_NAME`    | Branch or tag name that triggered the pipeline |
| `$CI_COMMIT_BRANCH`      | Branch name (empty for tags)                   |
| `$CI_COMMIT_TAG`         | Tag name (empty for branches)                  |
| `$CI_COMMIT_MESSAGE`     | Full git commit message                        |
| `$CI_PROJECT_NAME`       | Repository name                                |
| `$CI_PROJECT_NAMESPACE`  | Group/user the project belongs to              |
| `$CI_REGISTRY_IMAGE`     | Base URL for this project's container registry |
| `$CI_REGISTRY_USER`      | Username to authenticate with the registry     |
| `$CI_REGISTRY_PASSWORD`  | Password/token for the container registry      |
| `$CI_ENVIRONMENT_NAME`   | Name of the environment being deployed to      |
| `$CI_RUNNER_DESCRIPTION` | Name/description of the runner executing jobs  |
| `$GITLAB_USER_LOGIN`     | Username of who triggered the pipeline         |

## Variable Protection and Masking

- **Protected:** variable is only available in pipelines running on protected branches/tags
- **Masked:** value is never printed in job logs (replaces with `[MASKED]`)
- **Expand:** whether `$VAR` syntax inside the value is expanded

> Always mask credentials, tokens, and passwords. Always protect production secrets.

## Variable Precedence (highest wins)

1. Trigger variables
2. Scheduled pipeline variables
3. Manual pipeline variables (set when clicking "Run pipeline")
4. Project CI/CD variables
5. Group CI/CD variables
6. Instance CI/CD variables
7. `.gitlab-ci.yml` inline variables
8. Predefined CI/CD variables
