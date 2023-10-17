# Meli Play android

Challenge de android (Meli play)

## Requerimientos

- Java version 17
- Android Gradle 8.1.2
- Android Studio Giraffe

## Descripción

La aplicación cuenta con las funcionalidades de búsqueda y listado de productos de Mercado Libre

## Features

- Search
- Search List
- Detail

### Screenshots

<img src="screenshots/search_feature_image.png"/> <img src="screenshots/detail_feature_image.png"/>

## Como probar y correr el proyecto

- Check 
```
./gradlew lint
./gradlew check
./gradlew detekt
```

- Tests
```
./gradlew test
```

- Build app
```
./gradlew build
```

## Tecnologias/Librerias usadas

- Kotlin
- Kotlin DSL
- Retrofit
- Coroutines
- OkHttp
- Gson
- Coil
- Junit 5
- LeakCanary
- Detekt


### APIS usadas

- Search by query 
  - GET https://api.mercadolibre.com/sites/MLC/search?q=iphone

- Get item by ID
  - GET https://api.mercadolibre.com/items?ids=MLC12345

## Arquitectura

Arquitecture Modular basada en features. Para cada module se usa  Clean Arquitecture con enfoque en el flujo unidireccional

### Modulos

- Core
  - Network
  - Navigation

- Features
  - Search
  - Detail

### Capas ppr feature

- Data
- Domain
- Presentation
  - ui
  - di

## Documentacion/Referencias

- https://developer.android.com/topic/architecture?hl=es-419
- https://developer.android.com/topic/architecture/recommendations?hl=es-419
- https://developer.android.com/topic/modularization?hl=es-419