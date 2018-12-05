package com.example.heorhii_dubinin.news;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

class QueryUtils {
    private static final String TAG = "QueryUtils";
//    private static final String SAMPLE_JSON_RESPONSE = "{\"status\":\"ok\",\"totalResults\":20,\"articles\":[" +
//            "{\"source\":{\"id\":null,\"name\":\"Tsn.ua\"},\"author\":null,\"title\":\"Кабмін підвищив вартість електронної візи до України\",\"description\":null,\"url\":\"https://tsn.ua/ukrayina/kabmin-pidvischiv-vartist-elektronnoyi-vizi-do-ukrayini-1250508.html\",\"urlToImage\":null,\"publishedAt\":\"2018-11-16T09:58:49Z\",\"content\":null}," +
//            "{\"source\":{\"id\":null,\"name\":\"Espreso.tv\"},\"author\":null,\"title\":\"У київському торгівельному центрі обікрали народного депутата, - ЗМІ\",\"description\":null,\"url\":\"https://espreso.tv/news/2018/11/16/v_kyevskom_torgovom_centre_obokraly_narodnogo_deputata_smy\",\"urlToImage\":null,\"publishedAt\":\"2018-11-16T09:50:21Z\",\"content\":null}," +
//            "{\"source\":{\"id\":null,\"name\":\"Pravda.com.ua\"},\"author\":\"Українська правда\",\"title\":\"На пост глави автокефальної церкви претендує митрополит УПЦ МП - РосЗМІ\",\"description\":\"Митрополит Вінницький і Барський Симеон, який зустрічався з президентом України Петром Порошенком як представник ієрархів Української православної церкви Московського патріархату, вважається кандидатом на пост предстоятеля єдиної автокефальної церкви.\",\"url\":\"https://www.pravda.com.ua/news/2018/11/16/7198387/\",\"urlToImage\":\"https://img.pravda.com/images/doc/2/3/237e7c7-------.jpg\",\"publishedAt\":\"2018-11-16T09:24:52Z\",\"content\":\"Митрополит Вінницький і Барський Симеон, який зустрічався з президентом України Петром Порошенком як представник ієрархів Української православної церкви Московського патріархату, вважається кандидатом на пост предстоятеля єдиної автокефальної церкви. Про це … [+3762 chars]\"}," +
//            "{\"source\":{\"id\":null,\"name\":\"Ukrinform.ua\"},\"author\":null,\"title\":\"У КНДР випробували нову зброю - ЗМІ\",\"description\":\"Лідер Північної Кореї Кім Чен Ин проінспектував випробування розробленої нової високотехнологічної зброї.\",\"url\":\"https://www.ukrinform.ua/rubric-world/2581083-u-kndr-viprobuvali-novu-zbrou-zmi.html\",\"urlToImage\":\"https://static.ukrinform.com/photos/2017_05/thumb_files/630_360_1495426027-9580.jpg\",\"publishedAt\":\"2018-11-16T09:22:34Z\",\"content\":null}," +
//            "{\"source\":{\"id\":null,\"name\":\"Pravda.com.ua\"},\"author\":null,\"title\":\"Турчинов: Говорити з маріонетками не будемо, Путін \\\"ховає слона\\\"\",\"description\":null,\"url\":\"https://www.pravda.com.ua/news/2018/11/16/7198389/\",\"urlToImage\":null,\"publishedAt\":\"2018-11-16T09:17:20Z\",\"content\":null}," +
//            "{\"source\":{\"id\":null,\"name\":\"Radiosvoboda.org\"},\"author\":\"Радіо Свобода\",\"title\":\"САП заочно оголосила про підозру Продану\",\"description\":\"Спеціалізована антикорупційна прокуратура заочно оголосила про підозру колишньому виконувачу обов’язків голови Державної фіскальної служби Мирославу Продану. Як стверджують в антикорупційному органі,\",\"url\":\"https://www.radiosvoboda.org/a/news-sap-prodan/29604036.html\",\"urlToImage\":\"https://gdb.rferl.org/83B59F46-F294-4460-8361-2FC993B00778_cx0_cy10_cw0_w1200_r1_s.jpg\",\"publishedAt\":\"2018-11-16T09:17:00Z\",\"content\":\"Спеціалізована антикорупційна прокуратура заочно оголосила про підозру колишньому виконувачу обов’язків голови Державної фіскальної служби Мирославу Продану. Як стверджують в антикорупційному органі, Продан не з’явився на виклики для проведення слідчих і проц… [+1265 chars]\"}," +
//            "{\"source\":{\"id\":null,\"name\":\"Ukrinform.ua\"},\"author\":null,\"title\":\"Киян просять не панікувати - на вулицях два дні лунатимуть сирени\",\"description\":null,\"url\":\"https://www.ukrinform.ua/rubric-kyiv/2581072-kian-prosat-ne-panikuvati-na-vulicah-dva-dni-lunatimut-sireni.html\",\"urlToImage\":null,\"publishedAt\":\"2018-11-16T09:16:00Z\",\"content\":null}," +
//            "{\"source\":{\"id\":null,\"name\":\"Epravda.com.ua\"},\"author\":null,\"title\":\"Гройсман чекає новий транш МВФ в грудні\",\"description\":null,\"url\":\"https://www.epravda.com.ua/news/2018/11/16/642698/\",\"urlToImage\":null,\"publishedAt\":\"2018-11-16T09:09:30Z\",\"content\":null}," +
//            "{\"source\":{\"id\":null,\"name\":\"Pravda.com.ua\"},\"author\":\"Українська правда\",\"title\":\"Співлідерку угорців Закарпаття допитала СБУ за промову про \\\"фашистів\\\" в Україні\",\"description\":\"Одну із лідерок угорської громади Закарпаття, ректора угорського інституту в Берегові та депутата обласної ради Ілдіку Орос викликали до СБУ у зв’язку з її виступом минулого місяця в Будапешті.\",\"url\":\"https://www.pravda.com.ua/news/2018/11/16/7198386/\",\"urlToImage\":\"https://img.pravda.com/images/doc/0/b/0b0a296-0.jpg\",\"publishedAt\":\"2018-11-16T09:03:43Z\",\"content\":\"Одну із лідерок угорської громади Закарпаття, ректора угорського інституту в Берегові та депутата обласної ради Ілдіку Орос викликали до СБУ у зв’язку з її виступом минулого місяця в Будапешті. Про це повідомляє закарпатське видання Mukachevo.net. Як повідоми… [+1041 chars]\"}," +
//            "{\"source\":{\"id\":null,\"name\":\"Styler.rbc.ua\"},\"author\":null,\"title\":\"Нацбанк ввів в обіг шестикутні монети з унікальним сюжетом\",\"description\":null,\"url\":\"https://styler.rbc.ua/ukr/zhizn/natsbank-vvel-oborot-shestiugolnye-monety-1542356568.html\",\"urlToImage\":null,\"publishedAt\":\"2018-11-16T08:50:00Z\",\"content\":null}," +
//            "{\"source\":{\"id\":null,\"name\":\"Pravda.com.ua\"},\"author\":\"Українська правда\",\"title\":\"Пробні корпуси для БТРів: сталь \\\"натівська\\\", виробник державний\",\"description\":\"Держпідприємство “Київський бронетанковий завод” виготовив експериментальний корпус БТР-3ДА з броньованої сталі виробництва однієї з країн НАТО. Фото.\",\"url\":\"https://www.pravda.com.ua/news/2018/11/16/7198385/\",\"urlToImage\":\"https://img.pravda.com/images/doc/7/1/7198385_fb_image_ukr_2018_11_16_10_45_32.png\",\"publishedAt\":\"2018-11-16T08:47:18Z\",\"content\":\"Держпідприємство \\\"Київський бронетанковий завод\\\" виготовив експериментальний корпус БТР-3ДА з броньованої сталі виробництва однієї з країн НАТО. Про це повідомляє державний концерн \\\" Укроборонпром \\\", до складу якого входить це ДП. Експериментальний корпус, дл… [+1909 chars]\"}," +
//            "{\"source\":{\"id\":null,\"name\":\"Tsn.ua\"},\"author\":\"ТСН Редакція\",\"title\":\"Сектор торгівлі в Україні зобов'яжуть приймати безготівкові платежі\",\"description\":\"Серед визначень у проекті закону і мобільні додатки.\",\"url\":\"https://tsn.ua/groshi/sektor-torgivli-v-ukrayini-zobov-yazhut-priymati-bezgotivkovi-platezhi-1250469.html\",\"urlToImage\":\"https://img.tsn.ua/cached/1533906636/tsn-eee475b58a92ebc929777cc5bd155ea9/thumbs/1200x630/13/7e/e0e792f27c908053c2378cbe8e537e13.jpeg\",\"publishedAt\":\"2018-11-16T08:45:00Z\",\"content\":\"Серед визначень у проекті закону і мобільні додатки. В Україні сектор торгівлі зобов'яжуть приймати безготівкові платежі. Відповідний проект постанови розробило Міністерство економічного розвитку та торгівлі, передає Finclub. \\\"Суб'єкти господарювання, які зді… [+1791 chars]\"}," +
//            "{\"source\":{\"id\":null,\"name\":\"Ukrinform.ua\"},\"author\":null,\"title\":\"У Смілі зранку поновили роботи із запуску тепла - Зубко\",\"description\":\"У місті Сміла Черкаської області роботи по запуску теплопостачання відновлено у п&#39;ятницю, 16 жовтня, з 07:30 ранку.\",\"url\":\"https://www.ukrinform.ua/rubric-regions/2580991-u-smili-zranku-ponovili-roboti-iz-zapusku-tepla-zubko.html\",\"urlToImage\":\"https://static.ukrinform.com/photos/2018_10/thumb_files/630_360_1540908506-786.jpg\",\"publishedAt\":\"2018-11-16T08:16:00Z\",\"content\":null}," +
//            "{\"source\":{\"id\":null,\"name\":\"Pravda.com.ua\"},\"author\":\"Українська правда\",\"title\":\"Контрабандисти збили прикордонника. ДПС нагадала: можуть стріляти\",\"description\":\"Уночі 16 листопада на Закарпатті контрабандисти автомобілем на іноземній реєстрації збили прикордонника, військовослужбовець у важкому стані у комі.\",\"url\":\"https://www.pravda.com.ua/news/2018/11/16/7198380/\",\"urlToImage\":\"https://img.pravda.com/images/doc/6/8/6813d84-745a594-kontrabanda-prykordonnyky.jpg\",\"publishedAt\":\"2018-11-16T08:09:53Z\",\"content\":\"Уночі 16 листопада на Закарпатті контрабандисти автомобілем на іноземній реєстрації збили прикордонника, військовослужбовець у важкому стані у комі. Про це повідомив речник Держприкордонслужби Олег Слободян у Facebook. За його словами, уночі під час спільних … [+1418 chars]\"}," +
//            "{\"source\":{\"id\":null,\"name\":\"Molbuk.ua\"},\"author\":null,\"title\":\"Синоптики дали детальний прогноз на зиму в Україні\",\"description\":\"Завідувач відділу прикладної метеорології і кліматології Українського науково-дослідного гідрометеорологічного інституту Віра Балабух розповіла, що зима на українців чекає досить тепла.Але це не\",\"url\":\"https://lviv.molbuk.ua/ukraine/10611-synoptyky-daly-detalnyy-prognoz-na-zymu-v-ukrayini.html\",\"urlToImage\":\"https://lviv.molbuk.ua/uploads/posts/2018-11/medium/1542354234_pushistyy-kotik-smotrit-na-padayushhiy-sneg.jpg\",\"publishedAt\":\"2018-11-16T07:44:00Z\",\"content\":\"Але це не означає, що суворі морози не прийдуть до України, передає \\\"Обозреватель\\\" слова Балабух. \\\"Це не означає, що у нас зими не буде, у нас просто фон зими стає значно теплішим, але на цьому тлі можуть бути такі ж заходи холоду, які були в 70-80-х роках. А… [+764 chars]\"}," +
//            "{\"source\":{\"id\":null,\"name\":\"Tsn.ua\"},\"author\":\"ТСН Редакція\",\"title\":\"У Москві погрожують перекрити Азовське море для українських суден\",\"description\":\"Нині Україна заарештувала 15 суден за заходження до Криму.\",\"url\":\"https://tsn.ua/ukrayina/u-moskvi-pogrozhuyut-perekriti-azovske-more-dlya-ukrayinskih-suden-1250427.html\",\"urlToImage\":\"https://img.tsn.ua/cached/1538404044/tsn-0aac7b3bbd08e91384419d4ae49bc10f/thumbs/1200x630/21/59/950aee953e11f0aff5e81514662e5921.jpeg\",\"publishedAt\":\"2018-11-16T07:16:00Z\",\"content\":\"Нині Україна заарештувала 15 суден за заходження до Криму. У Росії погрожують перекрити для українських суден вихід з Азовського моря. Про це заявив член комітету з оборони і безпеки Ради Федерації РФ Франц Клинцевич, коментуючи затримання 15 суден українськи… [+2628 chars]\"}," +
//            "{\"source\":{\"id\":null,\"name\":\"Gordonua.com\"},\"author\":\"ГОРДОН\",\"title\":\"\uFEFFЗеленський про президентський рейтинг: У мене друге місце, у Тимошенко – перше. Нормальна гарна бійка\",\"description\":\"Шоумен Володимир Зеленський вперше прокоментував результати опитування, згідно з якими він вийшов на друге місце на президентських виборах в Україні, а лідер парламентської фракції \\\"Батьківщина\\\" Юлія Тимошенко на перше.\",\"url\":\"https://gordonua.com/ukr/news/politics/-zelenskij-pro-prezidentskomu-rejtingu-u-mene-druge-mistse-u-timoshenko-pershe-normalna-garna-bijka-508425.html\",\"urlToImage\":\"https://gordonua.com/img/article/5084/25_main.jpg\",\"publishedAt\":\"2018-11-16T07:12:04Z\",\"content\":\"Шоумен Володимир Зеленський, коментуючи ТСН 15 листопада останні дані соцопитувань, згідно з якими він виходить у другий тур президентських виборів в Україні, заявив, що \\\"як чоловік, зобов'язаний пропускати жінку вперед\\\". \\\"Ну, стосовно другого місця – боротис… [+1149 chars]\"}," +
//            "{\"source\":{\"id\":null,\"name\":\"Www.rbc.ua\"},\"author\":\"РБК-Україна\",\"title\":\"У Кабміні розповіли, коли запрацює накопичувальна система пенсій\",\"description\":\"\",\"url\":\"https://www.rbc.ua/ukr/news/kabmine-rasskazali-zarabotaet-nakopitelnaya-1542351663.html\",\"urlToImage\":\"https://www.rbc.ua/static/img/_/_/____22842_650x410_1_650x410.jpg\",\"publishedAt\":\"2018-11-16T07:01:46Z\",\"content\":\"Пенсійна система України готова до впровадження другого рівня Віце-прем'єр-міністр України Павло Розенко розповів, коли в Україні запрацює накопичувальна система загальнообов'язкового державного пенсійного страхування. Про це повідомляє РБК-Україна з посиланн… [+1210 chars]\"}," +
//            "{\"source\":{\"id\":null,\"name\":\"Radiosvoboda.org\"},\"author\":\"Марія Щур\",\"title\":\"Окупований Крим і скандал довкола прем'єра Чехії: син готовий свідчити проти батька\",\"description\":\"Протопопов хотів відмовити Енді від подорожі: «Кривий Ріг – це ж не земля обітована».\",\"url\":\"https://www.radiosvoboda.org/a/29602835.html\",\"urlToImage\":\"https://gdb.rferl.org/A1B81C45-E5D9-4336-B520-9E1D94A03F8A_cx0_cy3_cw0_w1200_r1_s.jpg\",\"publishedAt\":\"2018-11-15T19:13:47Z\",\"content\":\"Син чеського прем’єр-міністра Андрей Бабіш-молодший звинувачує свого батька у брехні та каже, що готовий свідчити у справі свого «викрадення». Бабіш-молодший стверджує, що його батько вигадує, ніби він є психічно хворий. Про це Андрей Бабіш-молодший написав в… [+10587 chars]\"}," +
//            "{\"source\":{\"id\":null,\"name\":\"Zik.ua\"},\"author\":null,\"title\":\"Головний рабин України наполягає, що за синагогою стежило таки НАБУ\",\"description\":null,\"url\":\"https://zik.ua/news/2018/11/15/golovnyy_rabyn_ukrainy_napolyagaie_shcho_za_synagogoyu_stezhylo_nabu_1449095\",\"urlToImage\":null,\"publishedAt\":\"2018-11-15T18:36:00Z\",\"content\":null}]}";

    static ArrayList<News> extractNews(String json) {

        ArrayList<News> breakingNews = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(json);
            JSONArray newsArray = baseJsonResponse.getJSONArray("articles");

            for (int i = 0; i < newsArray.length(); i++) {
                JSONObject currentNews = newsArray.getJSONObject(i);
                String title = currentNews.getString("title");
                String urlToImage = currentNews.getString("urlToImage");
                String sourceName = currentNews.getJSONObject("source").getString("name");
                String description = currentNews.getString("description");
                String publishedAt = currentNews.getString("publishedAt");

//                Date dateObject = new Date(publishedAt);
//                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");

                News news = new News(urlToImage, title, sourceName, description, publishedAt);
                breakingNews.add(news);
            }
            Log.d(TAG, "extractNews: " + breakingNews.size());

//            for (int i = 0; i < newsArray.length(); i++) {
//                JSONObject currentNews = newsArray.getJSONObject(i);
//                String urlToImage = currentNews.getString("urlToImage");
//                String title = currentNews.getString("title");
//                String sourceName = currentNews.getString("name");
//                String description = currentNews.getString("description");
//                String publishedAt = currentNews.getString("publishedAt");
//
//                Date dateObject = new Date(publishedAt);
//                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
//
//
//
//                News news = new News(urlToImage, title/*, sourceName, description, publishedAt*/);
//                breakingNews.add(news);
//            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }
        return breakingNews;
    }
}