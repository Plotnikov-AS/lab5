<#import "parts/page.ftlh" as p>

<@p.page>
    <h1>Выбор валюты</h1>
    <div>
        <form action="/getVal" method="post">

            Выберите валюту: <select name="valute">
                <#foreach valute in valutes>
                    <option value="${valute.getId()}">${valute.getName()}</option>
                </#foreach>
            </select>

            <input type="submit" value="Получить курс">
        </form>
    </div>
</@p.page>