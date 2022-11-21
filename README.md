Запуск квеста производился на Tomcat 9.0.65
Дополнительно реализован выбор языка игры при старте русский/английский.
Подключен Bootstrap , на нем alert сообщение при отсутствии имени игрока
и стартовая форма (имя игрока, выбор языка).
картинки подтверждения работоспособности пока в webapp/static/img
папка для логирования "C:/quest/logs/" или "D:/quest/logs/".
Отключение обработки ошибок(перенаправление на error.jsp) убрать в web.xml 
блоки  <!-- error-code related error pages --> и <!-- exception-type related error pages -->
Сюжет при выборе русского языка, в английском стоят сообщения заглушки.

Картинки работоспособности.


![Image alt](https://github.com/sfill70/ru.javarush.golf.quest/raw/master/src/main/webapp/static/img/start_page.png)
![Image alt](https://github.com/sfill70/ru.javarush.golf.quest/raw/master/src/main/webapp/static/img/quest_page.png)
![Image alt](https://github.com/sfill70/ru.javarush.golf.quest/raw/master/src/main/webapp/static/img/victory_page.png)
![Image alt](https://github.com/sfill70/ru.javarush.golf.quest/raw/master/src/main/webapp/static/img/loss.png)
![Image alt](https://github.com/sfill70/ru.javarush.golf.quest/raw/master/src/main/webapp/static/img/after_restart_page.png)


