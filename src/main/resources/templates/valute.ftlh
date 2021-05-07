<#import "parts/page.ftlh" as p>

<@p.page>
    <h1>${valute.getName()}</h1>
    <div>
        Номинал: ${valute.getNominal()}<br>
        Курс: ${valute.getValue()} руб.
    </div>
    <a href="javascript:history.back()">Назад</a>
</@p.page>