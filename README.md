# Проект автотестирования Scooter

Этот проект содержит автотесты для веб-приложения Самокат, компании Яндекс. Автотесты разработаны с использованием Java, Selenium и JUnit.

## Зависимости

Для сборки и запуска проекта вам понадобятся следующие зависимости:
- Java Development Kit (JDK) 8 или выше
- Maven
- Браузер (Chrome, Firefox, или Internet Explorer)

## Установка и запуск

1. Склонируйте репозиторий на свой локальный компьютер:
[https://github.com/IvanAndreevichPle/Samokat.git](https://github.com/IvanAndreevichPle/Samokat.git)

2. Перейдите в папку проекта:
```bash
cd scooter-test
```
3. Запустите автотесты с помощью Maven. Класс с набором тестов называется TestRunner и запускается командой:
```bash
mvn test -Dtest=TestRunner
```

Вы также можете указать свой браузер, добавив системное свойство `browser` с соответствующим значением. Например, для запуска тестов в браузере Firefox:

```bash
mvn test -Dbrowser=firefox -Dtest=TestRunner
```
Аналогично, для запуска теста с использованием Internet Explorer:

```bash
mvn test -Dbrowser=ie -Dtest=TestRunner
```
Если свойство "browser" не указано, будет использоваться браузер Chrome.
```bash
mvn test -Dtest=TestRunner
```

## Структура проекта

- `src/main/test/ru/practikum_services/qa_scooter/pages/` - пакет для классов страниц веб-приложения
- `src/main/test/ru/practikum_services/qa_scooter/parametrized/` - пакет для классов параметризованных тестов
- `src/main/test/ru/practikum_services/TestRunner.java` - основной тестовый класс

## Ваш вклад

Если вы хотите внести вклад в проект, пожалуйста, создайте форк репозитория, 
внесите свои изменения и отправьте пул-реквест. Мы будем рады
рассмотреть ваши предложения и улучшения.

