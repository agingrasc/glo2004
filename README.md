[![CircleCI](https://circleci.com/gh/agingrasc/glo2004/tree/dev.svg?style=svg)](https://circleci.com/gh/agingrasc/glo2004/tree/dev)
# glo2004
Dépôt du projet principal du cours de GLO-2004 Orienté Objet (A2016)

# Documentation IDE

## Intellij IDEA

- Définir Projects Default à l'ouverture de Intellij et définir une JVM
    Configure -> Projet Defaults -> Project Structure
- Importer projet -> Pointer racine dépôt -> Type Gradle -> Paramètre par défaut
- Ajouter un build, dans l'interface de projet (alt+1), clic droit sur _build.gradle_ et choisir _Create "build (1)"..._
- Passser comme argument de script le type de build: build, test, run

## Eclipse

- Ouvrir Eclipse marketplace et installer _Buildship_
- File -> Import -> Gradle -> Pointer racine dépôt
- Ajouter build: clic droit projet -> _Run as_ -> _Run Configurations ..._ -> Double clic _Gradle Project_
- Tâches gradle disponible: build, run, test
- Il y a dans la console, un onglet _Gradle Executions_, il énumère entre autre les tests qui échouent lors du build _test_

## Netbeans

- Ouvrir _Tools_ -> _Plugins_ -> chercher Gradle -> installer _Gradle Support_
- Ouvrir le projet -> _File_ -> _Open project_ -> racine du projet
- Les task gradle sont détectées automatiquement et peuvent être lancées en faisant un clic droit sur le projet et en sélectionnant _task_
