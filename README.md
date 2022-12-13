Запуск квеста производился на Tomcat 9.0.65
Настройки TomCat:
Вкладка Server: URL - http://localhost:8080/, HTTP port - 8080, JMX port - 1099
Вкладка Deployment: Окно-"Deploy at the server startup" - war:exploded, Application context - /
Дополнительно реализован выбор языка игры при старте русский/английский.
картинки подтверждения работоспособности пока в webapp/static/img
папка для логирования "C:/quest/logs/" или "D:/quest/logs/".

Отключение обработки ошибок(перенаправление на error.jsp) убрать в web.xml 
блоки  <!-- error-code related error pages --> и <!-- exception-type related error pages -->

Сюжет при выборе русского языка, в английском стоят сообщения заглушки.

прохождение на русском lav0(согласится)-> lev1(отказаться)-> lev2(отказаться)-> lev3(согласиться)-> lev4(откзаться)-> 
lev5(согласиться)-> victory



![Image alt](https://github.com/sfill70/ru.javarush.golf.quest/raw/master/src/main/webapp/static/img/start_page.png)
![Image alt](https://github.com/sfill70/ru.javarush.golf.quest/raw/master/src/main/webapp/static/img/quest_page.png)
![Image alt](https://github.com/sfill70/ru.javarush.golf.quest/raw/master/src/main/webapp/static/img/victory_page.png)
![Image alt](https://github.com/sfill70/ru.javarush.golf.quest/raw/master/src/main/webapp/static/img/loss.png)
![Image alt](https://github.com/sfill70/ru.javarush.golf.quest/raw/master/src/main/webapp/static/img/after_restart_page.png)
![Image alt](https://github.com/sfill70/ru.javarush.golf.quest/raw/master/src/main/webapp/static/img/jacoco.png)


