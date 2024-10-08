# Concept-Object-Simulation-MAS-Project
Simulation project based on Multi-agent-system

## Overview

This repository follows a branching strategy to ensure smooth collaboration and integration of features. The `main` branch is protected for release purposes, and the `develop` branch is used for integration. All new features should be developed on separate feature branches.

## Branching Strategy

### 1. `main` Branch
- **Purpose**: This branch is reserved for production-ready releases.
- **Protection**: The `main` branch is protected, meaning direct pushes are **not allowed**. All changes to `main` must come through pull requests (PRs) after thorough code review and approval.
- **Deployment**: Changes merged into `main` are automatically deployed (or manually deployed, depending on the project setup).

### 2. `develop` Branch
- **Purpose**: This branch is used for the integration of new features and bug fixes. It is the default branch for day-to-day development.
- **Merge Strategy**: Features should be merged into `develop` via pull requests after code review.
- **Testing**: Ensure that the `develop` branch always passes all tests and remains stable.

### 3. Feature Branches
- **Naming Convention**: Create feature branches from `develop` using the following pattern:
  ```
  feature/feature-name
  ```
  For example: `feature/login-page`.
- **Purpose**: Feature branches are used for developing new features or addressing issues.
- **Merge Process**: Once the feature is complete, create a pull request targeting `develop`, and ensure the following:
  - Code has been reviewed.
  - All tests pass.
  - The branch is up to date with `develop`.

## Workflow

1. **Creating a New Feature**:
   - Checkout the `develop` branch:
     ```bash
     git checkout develop
     git pull origin develop
     ```
   - Create a new feature branch:
     ```bash
     git checkout -b feature/your-feature-name
     ```
   - Develop your feature and commit changes as needed.

2. **Pushing Changes**:
   - Push your feature branch to the repository:
     ```bash
     git push origin feature/your-feature-name
     ```

3. **Creating a Pull Request**:
   - Once the feature is complete, open a pull request (PR) from your `feature/your-feature-name` branch to `develop`.
   - Ensure all the required checks (e.g., tests, code linting) pass before requesting a review.

4. **Code Review**:
   - Team members will review the PR. Make necessary changes based on feedback.

5. **Merging**:
   - Once the PR is approved, it can be merged into `develop`.

6. **Releasing to Main**:
   - When a set of features is ready for release, create a PR from `develop` to `main`.
   - Only after approval and passing tests will the changes be merged into `main`.

## Protected Branch Policy

The `main` branch is protected with the following rules:
- Direct commits are not allowed.
- Pull requests require at least one approval before merging.
- All status checks (e.g., CI/CD pipeline, unit tests) must pass before merging.

## Code Style and Best Practices

- Follow the coding standards and style guide provided in the repository.
- Use meaningful commit messages:
  - Bad: `fix bug`
  - Good: `Fix login button alignment issue on mobile`

## Testing

- Ensure all new features have corresponding unit/integration tests.
- Run tests before pushing changes:
  ```bash
  gradle test
  ```

## Issue Tracking

- Use GitHub Issues to track bugs, feature requests, and tasks.
- When starting a new feature or bug fix, link it to an issue in the format `#issue-number` in your pull request.

## Contribution Guidelines

- Fork the repository (if external contributor).
- Follow the branching and workflow guidelines described above.
- Ensure your changes are well-tested.
- Submit your pull request for review once your feature is complete.

## License

This project is licensed under the Apache 2.0 License. See the `LICENSE` file for more details.

