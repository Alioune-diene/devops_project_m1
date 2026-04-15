# JNDArray
<p align="center">
  <a href="https://github.com/Alioune-diene/devops_project_m1/actions/workflows/ci.yml/badge.svg">
    <img src="https://github.com/Alioune-diene/devops_project_m1/actions/workflows/ci.yml/badge.svg" alt="CI" />
  </a>
  <a href="https://alioune-diene.github.io/devops_project_m1/">
    <img src="https://img.shields.io/badge/Javadoc-published-blue" alt="Javadoc" />
  </a>
  <a href="https://codecov.io/gh/Alioune-diene/devops_project_m1">
    <img src="https://codecov.io/gh/Alioune-diene/devops_project_m1/branch/main/graph/badge.svg" alt="codecov" />
  </a>
  <a href="https://im2ag-sonar.univ-grenoble-alpes.fr/dashboard?id=JNDArray">
    <img src="https://im2ag-sonar.univ-grenoble-alpes.fr/api/project_badges/measure?project=JNDArray&metric=alert_status&token=sqb_6351d5a808563293ecfec9365475e66c9abc1ebc" alt="Quality Gate" />
  </a>
  
  <a href="https://im2ag-sonar.univ-grenoble-alpes.fr/dashboard?id=JNDArray">
    <img src="https://im2ag-sonar.univ-grenoble-alpes.fr/api/project_badges/measure?project=JNDArray&metric=software_quality_reliability_rating&token=sqb_6351d5a808563293ecfec9365475e66c9abc1ebc" alt="Reliability rating" />
  </a>
 <a href="https://im2ag-sonar.univ-grenoble-alpes.fr/dashboard?id=JNDArray">
    <img src="https://im2ag-sonar.univ-grenoble-alpes.fr/api/project_badges/measure?project=JNDArray&metric=software_quality_security_rating&token=sqb_6351d5a808563293ecfec9365475e66c9abc1ebc" alt="Security rating" />
  </a>
<a href="https://im2ag-sonar.univ-grenoble-alpes.fr/dashboard?id=JNDArray">
    <img src="https://im2ag-sonar.univ-grenoble-alpes.fr/api/project_badges/measure?project=JNDArray&metric=software_quality_maintainability_rating&token=sqb_6351d5a808563293ecfec9365475e66c9abc1ebc" alt="Maintainability rating" />
  </a>
  
</p>   


A scientific computing library for Java inspired by [NumPy](https://numpy.org/), providing multidimensional array support.

## Features

### `NDArray`

The core data structure — a multidimensional array of `float` values supporting 1D (vector) and 2D (matrix) shapes.

**Attributes:**

| Attribute | Type | Description |
|-----------|------|-------------|
| `ndim` | `int` | Number of dimensions (1 or 2) |
| `shape()` | `int[]` | Length of each dimension |
| `size` | `int` | Total number of elements |

### Creation functions

| Method | Description | Example |
|--------|-------------|---------|
| `NDArray.array(float... values)` | 1D array from values | `NDArray.array(1f, 2f, 3f)` |
| `NDArray.array(float[][] values)` | 2D array from row-major 2D array | `NDArray.array(new float[][]{{1f,2f},{3f,4f}})` |
| `NDArray.zeros(int... shape)` | Array filled with zeros | `NDArray.zeros(3, 2)` |
| `NDArray.arange(int stop)` | Values in `[0, stop)` | `NDArray.arange(5)` |
| `NDArray.arange(float start, float stop, float step)` | Values in `[start, stop)` with step | `NDArray.arange(0f, 1f, 0.5f)` |

### Reshape

```java
NDArray a = NDArray.arange(6);        // [0. 1. 2. 3. 4. 5.]
NDArray b = a.reshape(2, 3);          // [[0. 1. 2.]
                                       //  [3. 4. 5.]]
```

### Display

`toString()` produces a NumPy-style representation:

```java
NDArray.array(1f, 2f, 3f).toString()
// → [1. 2. 3.]

NDArray.array(new float[][]{{1f, 2f}, {3f, 4f}}).toString()
// → [[1. 2.]
//    [3. 4.]]
```

## Usage

Add the dependency from GitHub Packages to your `pom.xml`:

```xml
<dependency>
  <groupId>com.groupe8.ttykm</groupId>
  <artifactId>jndarray</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

Configure the GitHub Packages repository:

```xml
<repositories>
  <repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/Alioune-diene/devops_project_m1</url>
  </repository>
</repositories>
```

## Tools

| Tool | Purpose | Why we chose it |
|------|---------|-----------------|
| Java 17 | Implementation language | LTS release, modern language features |
| Maven | Build, test, packaging, deployment | Standard Java build tool with rich plugin ecosystem |
| JUnit 5 | Unit testing | Industry standard for Java, supports parameterized tests |
| Jacoco | Code coverage | Native Maven integration, enforces minimum coverage threshold |
| GitHub Actions | CI/CD pipeline | Native GitHub integration, no external service required |
| Codecov | Coverage reporting | Posts coverage diffs on PRs, tracks trends over time |
| GitHub Packages | Artifact registry | Integrated with the repo, no external registry needed |
| GitHub Pages | Documentation hosting | Auto-publishes Maven site (Javadoc + test reports) on every merge |

## Git Workflow

We follow a **feature branch workflow**:

1. `main` is a protected branch s direct pushes are blocked.
2. Each feature or fix is developed on a dedicated branch: `feature/<short-description>` or `fix/<short-description>`.
3. Once ready, a **Pull Request** is opened targeting `main`.
4. The other team member reviews the code and must **approve** before merge.
5. **CI must pass** (build + tests + coverage check) before merge is allowed.

Branch naming examples:
- `feature/ndarray-core`
- `feature/methods`
- `feature/readme`
- `fix/addition-operator`

### PR validation checklist

- All tests pass (`mvn verify`)
- Coverage ≥ 60% (enforced by Jacoco)
- Code reviewed and approved by a team member
- No direct push to `main`

## Docker Images

> _To be filled in if Docker images are produced._

## Feedback

> _To be filled in at the end of the project._
