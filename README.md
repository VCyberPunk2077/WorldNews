# WorldNews

Android-приложение для просмотра новостей. Проект написан на Kotlin с использованием Jetpack Compose, Retrofit, Dagger и Navigation Compose.

## Возможности

- Загрузка новостей из удалённого API
- Отображение списка новостей
- Переход к детальной информации о новости
- Загрузка изображений новостей
- Обработка сетевого слоя через Retrofit
- Управление зависимостями через Dagger
- Навигация между экранами через Navigation Compose

## Стек технологий

- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- ViewModel
- Coroutines
- Retrofit
- Gson Converter
- OkHttp Logging Interceptor
- Dagger 2
- KAPT
- Coil

## Архитектура

Проект разделён на несколько основных слоёв:

### Data layer

Слой отвечает за получение данных из сети, DTO/модели, маппинг и реализацию репозиториев.

### Domain layer

Содержит бизнес-сущности, use case-классы и абстракции репозиториев.

### Presentation layer

Содержит UI на Jetpack Compose, состояние экранов и взаимодействие пользователя с приложением.

## Запуск проекта

1. Склонируйте репозиторий:

```bash
git clone https://github.com/VCyberPunk2077/WorldNews.git
```

2. Откройте проект в Android Studio.
3. Синхронизируйте Gradle.
4. Запустите приложение на эмуляторе или физическом устройстве.

## Особенности реализации

- UI полностью реализован на Jetpack Compose.
- Для сетевого слоя используется Retrofit с Gson-конвертером.
- Для логирования HTTP-запросов подключён OkHttp Logging Interceptor.
- DI реализован через Dagger 2.
- Навигация построена на Navigation Compose.
- Изображения загружаются через Coil.