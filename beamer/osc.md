---
title: Orientation, Science et Culture
subtitle: Institut Galilée - Licence Informatique
author: Jones Magloire
date: 15 Janvier 2024
theme: metropolis
toc: true
section-titles: false
pagestyle: empty
header-includes: |
  \newcommand{\hideFromPandoc}[1]{#1}
  \usepackage{fourier}
  \hideFromPandoc{ \let\Begin\begin \let\End\end }
  \metroset{block=fill}
  \usepackage{dirtytalk}
build: pandoc -f markdown -st beamer osc.md -o osc.pdf --lua-filter code-filter.lua
---

# Parcours universitaire

---

## Parcours universitaire

### Simple et efficace

- Bac S : Science de l'Ingénieur (2010)
- Licence (2010-2013)
  - Licence 1: Tronc Commun (chimie, physique, informatique, math)
  - Licence 2: Math et informatique
  - Licence 3: Informatique pur
    - Stage de 3 mois en laboratoire LIPN
- Master Programmation et Logiciels Sûrs (2013-2015)
  - Master professionnel ET recherche
    - Stage de 6 mois chez takima

# Parcours professionel

---

## Parcours professionel

### Takima{.alert}
- 6 mois de stage (2015)
  - 2 mois de formations
  - 4 mois sur projet

\centering
\includegraphics[height=1cm,keepaspectratio]{images/takima.png}

### Jawg Maps{.example}
- 2015-2021: DevOPS / Expert Cartographie
- 2022-: Chief Technical Officer (CTO / Directeur Technique)

\centering
\includegraphics[height=1cm,keepaspectratio]{images/jawg.png}

---

## Questions{.standout}

\centering\Huge Questions
