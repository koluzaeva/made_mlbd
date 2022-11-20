**Домашнее задание №3**

В рамках данного домашнего задания предлагается реализовать алгоритм линейной регрессии на Scala с использованием библиотеки Breeze.

Решение должно принимать на вход тренировочные и тестовые данные в виде файлов. Выход решения - предсказание модели предлагается также реализовать через файл. В процессе обучения модели рекомендуется организовать валидацию и сохранять вывод ее результатов.

Дерево проекта:

    ├──data
    |   ├──train.csv
    |   ├──test.csv
    ├──src
    |   ├──main
    |        ├──scala
    |             ├──main.scala
    ├──train_logs
    |    ├──learning_statistics.txt
    |    ├──prediction.txt
    ├──build.sbt
    ├──RADME.md

Модель линейной регрессии обучается на data/train.csv, сохраняет ошибки обучения и ошибки валидации по эпохам в train_logs/learning_statistics.txt. Результаты предсказаний для data/test.csv сохраняются в train_logs/prediction.txt.

Код реализован в main.scala (https://github.com/koluzaeva/made_mlbd/blob/main/HW3/src/main/scala/main.scala)
