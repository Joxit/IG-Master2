---
title: 'Cartographie et OpenStreetMap: Docker'
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
  \newcommand{\imagedirectory}{docker-images}
  \usepackage{dirtytalk}
---

# Docker

---

## Docker

> Docker est un outil qui peut empaqueter une application et ses dépendances dans un container isolé, qui pourra être exécuté sur n'importe quel serveur

- 451 Research

# Histoire de Docker

---

## Histoire de Docker

### Culture générale

- 2008: Création de la société française dotCloud par Solomon Hykes, Sébastien Pahl et Kamel Founadi (anciens d’Epitech)
- 2010: Présentation de leur projet interne "Docker" au Y Combinator et déménagement aux USA
- 2013: dotCloud devient Docker Inc.
- 2013: Première version de Docker
- 2015: Docker Inc valorisé à 1 Milliard de dollars

# Container vs Virtual Machine

---

## Container vs Virtual Machine

![](docker-images/container-vs-vm.png)

\pause

### Définitions

- Infrastructure = ordintauer / serveur
- Hypervisor: s'exécute soit sur l'infrastructure (type 1), soit via un logiciel dans un OS (type 2)
  - Type 1: KVM, Microsoft Hyper-V et VMware vSphere
  - Type 2: VMware Workstation et Oracle VirtualBox

## Container vs Virtual Machine

### Container{.example}

- Un container est léger:
  - Un seul OS sur l'infrastructure, les containeurs partagent le même kernel
  - Necessite moins de ressources (RAM, CPU, Disk)
- Sécurisé, isolation des app par défaut (communication possible)
- Installation simple, tout est géré par Docker
- Un standard qui peut être implémenté par d'autres
- Livrable déployable partout

\pause

### Cas d'utilisation

- Des équipes de Dev/DevOPS pour leur application
- Déployement sur des orchestrateurs (Kubernetes, Nomad, Rancher...)
- Disposer plusieurs containeurs sur la même infrastructure ou dans des VM (Cloud)

## Container vs Virtual Machine

### Virtual Machine{.example}

- Une VM a un OS complet:
  - Plusieurs OS incompatibles peuvent aller sur la même infrastructure
  - Necessite plus de ressources (RAM, CPU, Disk)
- Sécurisé, une VM est virtuellement une machine
- Pas de standard, installation complexe

\pause

### Cas d'utilisation

- Des Cloud Providers (Google Cloud, Amazon Web Services, OVH...)
  - Leur infrastructures sont des machines à 64 coeur ou plus
  - Ils vendent des VM de 1 coeur à 16 coeurs
- Un developpeur avec un ordinateur Linux qui a besoin ponctuellement d'un Windows (et vice-versa)

# Comment ça fonctionne ?

## Notions

### Lexique

- Dockerfile: recette pour créer une image docker, elle se base 99% du temps sur une autre image existante
- Image: archive en lecture seule contenant votre application (`docker build`)
  - Une image est identifiée par un identifiant ou son couple nom + tag
- Container: instance d'une image en cours d'execution, votre application est en route (`docker run`)
  - Un container est identifié par un identifiant ou son nom
- Docker Registry: répertoire en ligne d'images accessibles et utilisable ([hub.docker.com](https://hub.docker.com) par défaut)

## Notions

### Autour de Docker

- Docker Engine: le coeur de docker que vous devez [_installer_](https://docs.docker.com/engine/install/)
  - Docker Serveur: démon qui run sur votre machine après l'installation
  - Docker Client: ligne de commande pour gérer votre serveur Docker de votre machine
- Docker Desktop: application de bureau pour Windows, Mac OS et Linux (freemium)
- Orchestrateur: outil supplémentaire pour gérer la vie de vos containers

## Images: Dockerfile

```dockerfile
# Image source disponible sur hub.docker.com
FROM eclipse-temurin:17-jre-alpine

# Dossier de travail quand on sera dans le container
WORKDIR /usr/src/osm

# On execute une commande qui sera dans l'image finale
RUN apt-get update && apt-get install curl

# On copie de l'ordinateur vers l'image
COPY build/libs/osm-boot.jar osm-boot.jar

# La commande executée quand le container démarre
CMD java $JAVA_OPTS -jar osm-boot.jar
```

## Images: les layers

### Qu'est-ce que des layers ?{.example}

- Chaque instruction génère un layer qui est mis en cache
- Lors d'une mise à jour du dockerfile, uniquement les instructions après la modification sont rejoués
- Une image recréée avec le même couple nom + tag écrase l'ancienne
- On peut optimiser la création d'images
  - Quand on a plusieurs applications qui ont besoin des mêmes dépendences, on peut créer une image de base
  - C'est une bonne pratique de supprimer ce qui n'est pas nécessaire dans l'image

## Conclusion {.standout}

\centering\Huge Questions
