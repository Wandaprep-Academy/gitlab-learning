# 04 — GitLab Artifacts and Cache

## Artifacts vs Cache

| Feature  | Artifacts                                      | Cache                                    |
|----------|------------------------------------------------|------------------------------------------|
| Purpose  | Pass files between jobs; download after run    | Speed up jobs by reusing downloaded deps |
| Scope    | Per-job; explicitly passed via `needs:`/stage  | Shared across jobs and pipeline runs     |
| Storage  | GitLab server                                  | GitLab server (or distributed cache)     |
| Typical  | Build output, test reports, compiled binaries  | `node_modules/`, `.m2/`, pip wheels      |
| Expiry   | Set with `expire_in:`                          | Keyed by `key:` — invalidated explicitly |

## Artifact Report Types

GitLab can parse certain artifact formats and display them natively in the UI:

| Report type         | `artifact:reports:` key  | Displayed as                          |
|---------------------|--------------------------|---------------------------------------|
| JUnit test results  | `junit:`                 | Tests tab in MR and pipeline view     |
| Code coverage       | `coverage_report:`       | Coverage % badge, MR widget           |
| SAST findings       | `sast:`                  | Security tab                          |
| Dependency scan     | `dependency_scanning:`   | Security tab                          |
| Container scan      | `container_scanning:`    | Security tab                          |
| Terraform plan      | `terraform:`             | MR widget shows plan summary          |
| Performance         | `performance:`           | MR performance widget                 |
