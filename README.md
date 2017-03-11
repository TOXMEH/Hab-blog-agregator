#Hab-blog-agregator

Программа для просмотра статей с ресурса habrahabr.ru.

**Основная задача программы:**
Показ пользователю статей с интересущими его хабами вне зависимости от даты публикации, рейтинга и количества просмотров статьи на ресурсе habrahabr.ru.
Программа обеспечивает показ хабов, на которые пользователь подписан, с примерно одинаковой частотой.

**В рамках данной программы пользователь может:**
- добавить хабы в список подписок пользователя
- получить список статей, сгенерированный программой
- изменить число показываемых статей с хабами, на которые пользователь подписан
- изменить число показываемых статей с хабами, на которые пользователь не подписан

Пользователь добавляет хабы в список подписок пользователя из общего списка хабов, которые берутся с ресурса habrahabr.ru

**Программа генерирует  статьи для показа пользователю согласно следующих правил:**
- ведется рейтинг показа хабов пользователю, в формате таблицы: Название хаба - число показов хаба пользователю
- при показе статьи в рейтинге хабов счетчик каждого хаба, к которому принадлежит статья увеличивается на 1
- при генерации списка показываемых пользователю статей берется хаб, на который подписан пользователь, и который имеет самый низкий показатель в рейтинге хабов, и случайным образом оттуда выбирается статья. Затем статья добавляется в список показываемых статей, перерасчитывается рейтинг хабов и повторно выбирается статья для показа пользователю
- то же проделывается и для хабов, на которые пользователь не подписан

**Требования к клиентской части приложения**
- Программа должна создавать html страницу 
- Программа должна являться SPA, т.е. деятельность программы долдно быть ограничено рамками одной html страницы
- при заходе пользователя на сайт должен быть выведен список статей. Если пользователь авторизован в системе, то система должна вывести список релевантных ему статей, иначе система должна вывести самые популярные статьи за сутки на сайте habrahabr.ru. Список статей должен быть представлен в виде блоков, состоящих из заголовка статьи, главной картинки статьи и списка хабов статьи
- с первоначального экрана пользователь может авторизоваться
- после авторизации пользователь может изменить свой список подписок на хабы и поменять пароль


