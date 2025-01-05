---
title: 'Cartographie et OpenStreetMap: Bonnes Pratiques'
subtitle: Institut Galilée - Master 2 P2S
author: Jones Magloire
date: 6 Janvier 2025
theme: metropolis
toc: true
section-titles: false
pagestyle: empty
header-includes: |
  \newcommand{\hideFromPandoc}[1]{#1}
  \usepackage{fourier}
  \hideFromPandoc{ \let\Begin\begin \let\End\end }
  \metroset{block=fill}
  \newcommand{\sectionimage}{Foo}
  \newcommand{\imagedirectory}{best-practices-images}
  \usepackage{dirtytalk}
---

# Semantic Versioning

## Semantic Versioning

### Qu'est-ce que c'est ?

C'est un schéma de version constitué de trois parties numériques `Major.Minor.Patch`, exemple `1.0.2`.

On peut y apposer une balise de pré-version facultative et une balise méta de construction facultative, exemple `2.4.1-beta`.

## Semantic Versioning: Règles

### Comment ça fonctionne ?{.example}

- Quand une release contient **des modifications radicales** (non retrocompatibles), on augmente la Major.
- Quand une release contient **des fonctionnalités**, on augmente la Minor.
- Quand une release contient **que des corrections**, on augment on augmente le Patch.

# Conventional Commits

## Conventional Commits

### Qu'est-ce que c'est ?

C'est une convention qui fournit un ensemble de règles simples pour **créer un historique de commits explicite**.

Ceci permet une utilisation d'outils automatisés pour la génération de **notes de version**.

Cette convention s'accorde avec **SemVer**, en décrivant les fonctionnalités (features), les correctifs (fixes) et les modifications radicales (breaking changes) apportées aux messages de validation.

## Conventional Commits: Format et exemple

```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

```
feat(osm): create endpoint for poi updates

Data is taken from OpenStreetMap and processed
by the API

BREAKING CHANGE: the new endpoint uses GeoJSON
```

## Conventional Commits: Les types fréquents

| Type                   | Description                              |
| ---------------------- | ---------------------------------------- |
| `feat`                 | Ajout de fonctionnalités                 |
| `fix`                  | Correction de bugs                       |
| `chore`                | Changements insignifiants                |
| `refactor`             | Cleanup code                             |
| `build`                | Changements sur la compilation du projet |
| `ci`                   | Continuous Integration                   |
| `style`                | Changements CSS uniquement               |
| `docs`, `perf`, `test` | Documentation, performance et test       |

## Conventional Commits

\centering\Large

> Spécifications complètes disponible sur le site
>
> [conventionalcommits.org](https://www.conventionalcommits.org/)

# Code Formatting

## Code Formatting

### Qu'est-ce que c'est ?

C'est un ensemble de règles ou de lignes directrices utilisées lors de l'écriture du code source d'un programme informatique.

Généralement on utilise des outils pour formatter le code automatiquement en respectant ces règles.

## Code Formatting: Règles

### Éléments à configurer

- Indentation: tabulations vs 4 espaces vs 2 espaces
- Les sauts de lignes après ou avant les accolades `{`
- Les espaces autours des caractères spéciaux (accolades `{}`, parenthèses `()`, etc.)
- Longueur des lignes: 80 charactères vs 100 charactères vs 120 charactères

## Code Formatting: Comment formater ?

### Outils disponibles

- Prettier:
  - Supporte: `JavaScript`, `TypeScript`, `JSX`, `JSON`, `CSS`, `HTML`...
  - Disponible en plugin pour: VSCode, Atom, WebStorm, CLI (node)...
- IDE (IntelliJ, Eclipse...):
  - Configuration par langage en local à la main
  - Configuration par le support de [EditorConfig](https://editorconfig.org/) ou similaire pour le projet

## Code Formatting: Bonnes pratiques{.standout}

\centering\Large
Faut-il personnaliser le style ?
