# Система Управления Банковскими Счетами

Система Управления Банковскими Счетами - это приложение на Java, которое предоставляет простой интерфейс для выполнения операций CRUD (Создание, Чтение, Обновление, Удаление) с банковскими счетами. Оно использует JDBC для подключения к базе данных SQL Server и демонстрирует базовое взаимодействие с базой данных на Java.

## Функциональные возможности

- Добавление новых банковских счетов.
- Обновление баланса существующих банковских счетов.
- Удаление банковских счетов.
- Получение и отображение деталей банковских счетов.

## Предварительные требования

Прежде чем начать, убедитесь, что вы выполнили следующие требования:
- На вашей машине установлен Java JDK 8 или новее.
- SQL Server запущен локально или доступен удаленно.
- Gradle (если вы хотите собрать проект с помощью Gradle).

## Установка Системы Управления Банковскими Счетами

Для установки Системы Управления Банковскими Счетами выполните следующие шаги:

1. Клонируйте репозиторий на вашу локальную машину:

git clone [<link>](https://github.com/Iogsothot/AQADockerBank.git)
2. Перейдите в директорию проекта:

cd bank-account-management
## Настройка базы данных

1. Создайте новую базу данных с именем `Bank` в вашем экземпляре SQL Server.

## Конфигурация приложения

1. Обновите детали подключения к базе данных в классе `FinancialDBAccess.java`, чтобы они соответствовали конфигурации вашего SQL Server.

## Запуск приложения

gradle run
