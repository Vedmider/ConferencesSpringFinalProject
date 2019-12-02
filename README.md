# ConferencesSpringFinalProject

Conference project: 
Система Управления Конференциями. Существуют роли: Администратор, Модератор, Спикер и обычный Пользователь. Модератор может закреплять, предлагать, изменять тему доклада за спикером, а также регулировать время и место проведения мероприятия. Необходимо учесть возможность просмотра прошедших/будущих митингов. У каждого Спикера существует свой рейтинг, в зависимости от рейтинга начисляется больше бонусов. Спикер может предлагать свой Доклад. Должна быть статистика зарегистрировавшихся людей и сколько физически всего пришли на Доклад. Реализовать уведомление участников о предстоящих события.

How to install:
Clone project
Run schema.sql from resources/ folder
Run populate.sql from resources/data/ folder
Run in terminal command a. mvn clean spring-boot:run:run b. ? Add configuration / command
Go to link localhost:8888/conferences
Conferences web app. Manage conferences events. 

Functionality:
Create/update/delete conferences events.
Create/update/delete reports to conferences events.
Create/update/delete site users with different roles: ADMINISTRATION, SPEAKERS, USERS, MODERATORS.
Anonymous users have acces only to index page with login. USER have acces to page with speakers and conferences list SPEAKER have acces to Admin page and Speakers page MODERATOR users can update/create reports ADMINISTRATOR can update/delete/create conferences, reports and users.
There are different levels of access to application pages based on users role.
There are login, logout and registration functionality.
