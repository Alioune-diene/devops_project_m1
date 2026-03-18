# JNDArray

![CI](https://github.com/Alioune-diene/devops_project_m1/actions/workflows/ci.yml/badge.svg)

A scientific computing library for Java inspired by [NumPy](https://numpy.org/), providing multidimensional array support.

## Features

> _This section will be filled in as features are implemented._

- `NDArray` — multidimensional array supporting 1D (vector) and 2D (matrix) with `float` data
  - Attributes: `ndim`, `shape`, `size`
- Creation functions: `array()`, `zeros()`, `arange()`
- Basic operations: `+`, `+=`
- `reshape()`

## Usage

> _Examples will be added here._

## Tools

| Tool | Purpose |
|------|---------|
| Java 17 | Implementation language |
| Maven | Build, test, and packaging |
| JUnit 5 | Unit testing |
| Jacoco | Code coverage (target: ≥ 80%) |
| GitHub Actions | CI/CD pipeline |

## Git Workflow

We follow a **feature branch workflow**:

1. `main` is the protected branch so no direct pushes allowed.
2. Each new feature or fix is developed on a dedicated branch: `feature/<short-description>`.
3. Once ready, a **Pull Request** is opened targeting `main`.
4. The other team member reviews the code and must approve before merging.
5. CI must pass (build + tests + coverage check) before merge is allowed.

Branch naming examples:
- `feature/ndarray-core`
- `feature/creation-functions`
- `feature/reshape`
- `fix/addition-operator`

## Docker Images

> _To be filled in if Docker images are produced._

## Feedback

> _To be filled in at the end of the project._
