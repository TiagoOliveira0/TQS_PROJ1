package com.demo.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class AirQualityGeoLocationServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AirQualityGeoLocationServiceImpl airQualityGeoLocationService;

    @Value("${api2.key}")
    private String api2Key;

    @Test
    void whenGetCoordsByCity_ThenCityShouldBeProvided(){
        City c1 = new City("40.5284837","-8.7655529","Aveiro");

        String json = "{\"documentation\":\"https://opencagedata.com/api\",\"licenses\":[{\"name\":\"see attribution guide\",\"url\":\"https://opencagedata.com/credits\"}],\"rate\":{\"limit\":2500,\"remaining\":2473,\"reset\":1620604800},\"results\":[{\"annotations\":{\"DMS\":{\"lat\":\"40\\u00b0 38' 25.78560'' N\",\"lng\":\"8\\u00b0 39' 13.62276'' W\"},\"MGRS\":\"29TNE2927498907\",\"Maidenhead\":\"IN50qp13nr\",\"Mercator\":{\"x\":-963334.839,\"y\":4931622.83},\"OSM\":{\"edit_url\":\"https://www.openstreetmap.org/edit?relation=5325138#map=17/40.64050/-8.65378\",\"note_url\":\"https://www.openstreetmap.org/note/new#map=17/40.64050/-8.65378&layers=N\",\"url\":\"https://www.openstreetmap.org/?mlat=40.64050&mlon=-8.65378#map=17/40.64050/-8.65378\"},\"UN_M49\":{\"regions\":{\"EUROPE\":\"150\",\"PT\":\"620\",\"SOUTHERN_EUROPE\":\"039\",\"WORLD\":\"001\"},\"statistical_groupings\":[\"MEDC\"]},\"callingcode\":351,\"currency\":{\"alternate_symbols\":[],\"decimal_mark\":\",\",\"html_entity\":\"&#x20AC;\",\"iso_code\":\"EUR\",\"iso_numeric\":\"978\",\"name\":\"Euro\",\"smallest_denomination\":1,\"subunit\":\"Cent\",\"subunit_to_unit\":100,\"symbol\":\"\\u20ac\",\"symbol_first\":0,\"thousands_separator\":\".\"},\"flag\":\"\\ud83c\\uddf5\\ud83c\\uddf9\",\"geohash\":\"ez1z5n6s01wcsmgm7qbm\",\"qibla\":100.15,\"roadinfo\":{\"drive_on\":\"right\",\"speed_in\":\"km/h\"},\"sun\":{\"rise\":{\"apparent\":1620537900,\"astronomical\":1620531360,\"civil\":1620536100,\"nautical\":1620533820},\"set\":{\"apparent\":1620589080,\"astronomical\":1620595620,\"civil\":1620590880,\"nautical\":1620593160}},\"timezone\":{\"name\":\"Europe/Lisbon\",\"now_in_dst\":1,\"offset_sec\":3600,\"offset_string\":\"+0100\",\"short_name\":\"WEST\"},\"what3words\":{\"words\":\"compiled.suffer.coaster\"},\"wikidata\":\"Q485581\"},\"bounds\":{\"northeast\":{\"lat\":40.7275536,\"lng\":-8.5209649},\"southwest\":{\"lat\":40.5284837,\"lng\":-8.7655529}},\"components\":{\"ISO_3166-1_alpha-2\":\"PT\",\"ISO_3166-1_alpha-3\":\"PRT\",\"_category\":\"place\",\"_type\":\"city\",\"city\":\"Aveiro\",\"continent\":\"Europe\",\"country\":\"Portugal\",\"country_code\":\"pt\",\"county\":\"Aveiro\",\"county_code\":\"AVR\",\"political_union\":\"European Union\",\"state\":\"Centro\",\"state_district\":\"Baixo Vouga\"},\"confidence\":4,\"formatted\":\"Aveiro, Portugal\",\"geometry\":{\"lat\":40.640496,\"lng\":-8.6537841}},{\"annotations\":{\"DMS\":{\"lat\":\"40\\u00b0 40' 44.94144'' N\",\"lng\":\"8\\u00b0 31' 18.17976'' W\"},\"MGRS\":\"29TNF4041803250\",\"Maidenhead\":\"IN50rq72jx\",\"Mercator\":{\"x\":-948633.154,\"y\":4937273.263},\"OSM\":{\"edit_url\":\"https://www.openstreetmap.org/edit?relation=3920249#map=17/40.67915/-8.52172\",\"note_url\":\"https://www.openstreetmap.org/note/new#map=17/40.67915/-8.52172&layers=N\",\"url\":\"https://www.openstreetmap.org/?mlat=40.67915&mlon=-8.52172#map=17/40.67915/-8.52172\"},\"UN_M49\":{\"regions\":{\"EUROPE\":\"150\",\"PT\":\"620\",\"SOUTHERN_EUROPE\":\"039\",\"WORLD\":\"001\"},\"statistical_groupings\":[\"MEDC\"]},\"callingcode\":351,\"currency\":{\"alternate_symbols\":[],\"decimal_mark\":\",\",\"html_entity\":\"&#x20AC;\",\"iso_code\":\"EUR\",\"iso_numeric\":\"978\",\"name\":\"Euro\",\"smallest_denomination\":1,\"subunit\":\"Cent\",\"subunit_to_unit\":100,\"symbol\":\"\\u20ac\",\"symbol_first\":0,\"thousands_separator\":\".\"},\"flag\":\"\\ud83c\\uddf5\\ud83c\\uddf9\",\"geohash\":\"ez1zqj6tjx783q9pyv1w\",\"qibla\":100.29,\"roadinfo\":{\"drive_on\":\"right\",\"speed_in\":\"km/h\"},\"sun\":{\"rise\":{\"apparent\":1620537840,\"astronomical\":1620531300,\"civil\":1620536040,\"nautical\":1620533820},\"set\":{\"apparent\":1620589020,\"astronomical\":1620595620,\"civil\":1620590880,\"nautical\":1620593100}},\"timezone\":{\"name\":\"Europe/Lisbon\",\"now_in_dst\":1,\"offset_sec\":3600,\"offset_string\":\"+0100\",\"short_name\":\"WEST\"},\"what3words\":{\"words\":\"hone.former.rebel\"},\"wikidata\":\"Q210527\"},\"bounds\":{\"northeast\":{\"lat\":41.0801546,\"lng\":-8.0891722},\"southwest\":{\"lat\":40.2788096,\"lng\":-8.7840289}},\"components\":{\"ISO_3166-1_alpha-2\":\"PT\",\"ISO_3166-1_alpha-3\":\"PRT\",\"_category\":\"place\",\"_type\":\"county\",\"continent\":\"Europe\",\"country\":\"Portugal\",\"country_code\":\"pt\",\"county\":\"Aveiro\",\"county_code\":\"AVR\",\"political_union\":\"European Union\",\"state\":\"Centro\",\"state_district\":\"Baixo Vouga\"},\"confidence\":1,\"formatted\":\"Aveiro, Portugal\",\"geometry\":{\"lat\":40.6791504,\"lng\":-8.5217166}},{\"annotations\":{\"DMS\":{\"lat\":\"3\\u00b0 36' 10.44540'' S\",\"lng\":\"55\\u00b0 19' 41.08440'' W\"},\"MGRS\":\"21MXS8570401595\",\"Maidenhead\":\"GI26ij05ph\",\"Mercator\":{\"x\":-6159093.581,\"y\":-398654.552},\"OSM\":{\"edit_url\":\"https://www.openstreetmap.org/edit?relation=185570#map=17/-3.60290/-55.32808\",\"note_url\":\"https://www.openstreetmap.org/note/new#map=17/-3.60290/-55.32808&layers=N\",\"url\":\"https://www.openstreetmap.org/?mlat=-3.60290&mlon=-55.32808#map=17/-3.60290/-55.32808\"},\"UN_M49\":{\"regions\":{\"AMERICAS\":\"019\",\"BR\":\"076\",\"LATIN_AMERICA\":\"419\",\"SOUTH_AMERICA\":\"005\",\"WORLD\":\"001\"},\"statistical_groupings\":[\"LEDC\"]},\"callingcode\":55,\"currency\":{\"decimal_mark\":\",\",\"html_entity\":\"R$\",\"iso_code\":\"BRL\",\"iso_numeric\":\"986\",\"name\":\"Brazilian Real\",\"smallest_denomination\":5,\"subunit\":\"Centavo\",\"subunit_to_unit\":100,\"symbol\":\"R$\",\"symbol_first\":1,\"thousands_separator\":\".\"},\"flag\":\"\\ud83c\\udde7\\ud83c\\uddf7\",\"geohash\":\"6z2esbp7d66p7utpzu1t\",\"qibla\":68.82,\"roadinfo\":{\"drive_on\":\"right\",\"speed_in\":\"km/h\"},\"sun\":{\"rise\":{\"apparent\":1620553200,\"astronomical\":1620548880,\"civil\":1620551880,\"nautical\":1620550380},\"set\":{\"apparent\":1620596160,\"astronomical\":1620600480,\"civil\":1620597420,\"nautical\":1620598920}},\"timezone\":{\"name\":\"America/Santarem\",\"now_in_dst\":0,\"offset_sec\":-10800,\"offset_string\":\"-0300\",\"short_name\":\"-03\"},\"what3words\":{\"words\":\"deflecting.boozing.blossomed\"},\"wikidata\":\"Q790342\"},\"bounds\":{\"northeast\":{\"lat\":-3.2211765,\"lng\":-55.0373984},\"southwest\":{\"lat\":-4.3051198,\"lng\":-56.9560834}},\"components\":{\"ISO_3166-1_alpha-2\":\"BR\",\"ISO_3166-1_alpha-3\":\"BRA\",\"_category\":\"place\",\"_type\":\"city\",\"continent\":\"South America\",\"country\":\"Brazil\",\"country_code\":\"br\",\"municipality\":\"Microrregi\\u00e3o de Itaituba\",\"region\":\"North Region\",\"state\":\"Par\\u00e1\",\"state_code\":\"PA\",\"state_district\":\"Regi\\u00e3o Geogr\\u00e1fica Intermedi\\u00e1ria de Santar\\u00e9m\",\"town\":\"Aveiro\"},\"confidence\":1,\"formatted\":\"Aveiro, Regi\\u00e3o Geogr\\u00e1fica Intermedi\\u00e1ria de Santar\\u00e9m, Brazil\",\"geometry\":{\"lat\":-3.6029015,\"lng\":-55.328079}},{\"annotations\":{\"DMS\":{\"lat\":\"40\\u00b0 38' 36.15036'' N\",\"lng\":\"8\\u00b0 38' 25.82340'' W\"},\"MGRS\":\"29TNE3039699231\",\"Maidenhead\":\"IN50qp34dj\",\"Mercator\":{\"x\":-961856.783,\"y\":4932043.577},\"OSM\":{\"edit_url\":\"https://www.openstreetmap.org/edit?way=152191044#map=17/40.64338/-8.64051\",\"note_url\":\"https://www.openstreetmap.org/note/new#map=17/40.64338/-8.64051&layers=N\",\"url\":\"https://www.openstreetmap.org/?mlat=40.64338&mlon=-8.64051#map=17/40.64338/-8.64051\"},\"UN_M49\":{\"regions\":{\"EUROPE\":\"150\",\"PT\":\"620\",\"SOUTHERN_EUROPE\":\"039\",\"WORLD\":\"001\"},\"statistical_groupings\":[\"MEDC\"]},\"callingcode\":351,\"currency\":{\"alternate_symbols\":[],\"decimal_mark\":\",\",\"html_entity\":\"&#x20AC;\",\"iso_code\":\"EUR\",\"iso_numeric\":\"978\",\"name\":\"Euro\",\"smallest_denomination\":1,\"subunit\":\"Cent\",\"subunit_to_unit\":100,\"symbol\":\"\\u20ac\",\"symbol_first\":0,\"thousands_separator\":\".\"},\"flag\":\"\\ud83c\\uddf5\\ud83c\\uddf9\",\"geohash\":\"ez1z5quhvd72hmvjunrp\",\"qibla\":100.16,\"roadinfo\":{\"drive_on\":\"right\",\"road\":\"Avenida Vasco Branco\",\"speed_in\":\"km/h\"},\"sun\":{\"rise\":{\"apparent\":1620537900,\"astronomical\":1620531360,\"civil\":1620536100,\"nautical\":1620533820},\"set\":{\"apparent\":1620589080,\"astronomical\":1620595620,\"civil\":1620590880,\"nautical\":1620593160}},\"timezone\":{\"name\":\"Europe/Lisbon\",\"now_in_dst\":1,\"offset_sec\":3600,\"offset_string\":\"+0100\",\"short_name\":\"WEST\"},\"what3words\":{\"words\":\"lentil.implore.fries\"},\"wikidata\":\"Q800417\"},\"bounds\":{\"northeast\":{\"lat\":40.6443139,\"lng\":-8.6398755},\"southwest\":{\"lat\":40.6424359,\"lng\":-8.6412444}},\"components\":{\"ISO_3166-1_alpha-2\":\"PT\",\"ISO_3166-1_alpha-3\":\"PRT\",\"_category\":\"transportation\",\"_type\":\"railway\",\"city\":\"Aveiro\",\"continent\":\"Europe\",\"country\":\"Portugal\",\"country_code\":\"pt\",\"county\":\"Aveiro\",\"county_code\":\"AVR\",\"political_union\":\"European Union\",\"postcode\":\"3800-134\",\"railway\":\"Aveiro\",\"road\":\"Avenida Vasco Branco\",\"state\":\"Centro\",\"state_district\":\"Baixo Vouga\",\"suburb\":\"Vera Cruz\",\"village\":\"Esgueira\"},\"confidence\":9,\"formatted\":\"Aveiro, Avenida Vasco Branco, 3800-134 Aveiro, Portugal\",\"geometry\":{\"lat\":40.6433751,\"lng\":-8.6405065}},{\"annotations\":{\"DMS\":{\"lat\":\"40\\u00b0 38' 36.02724'' N\",\"lng\":\"8\\u00b0 38' 25.41516'' W\"},\"MGRS\":\"29TNE3040599227\",\"Maidenhead\":\"IN50qp34dj\",\"Mercator\":{\"x\":-961844.16,\"y\":4932038.579},\"OSM\":{\"edit_url\":\"https://www.openstreetmap.org/edit?node=1650269000#map=17/40.64334/-8.64039\",\"note_url\":\"https://www.openstreetmap.org/note/new#map=17/40.64334/-8.64039&layers=N\",\"url\":\"https://www.openstreetmap.org/?mlat=40.64334&mlon=-8.64039#map=17/40.64334/-8.64039\"},\"UN_M49\":{\"regions\":{\"EUROPE\":\"150\",\"PT\":\"620\",\"SOUTHERN_EUROPE\":\"039\",\"WORLD\":\"001\"},\"statistical_groupings\":[\"MEDC\"]},\"callingcode\":351,\"currency\":{\"alternate_symbols\":[],\"decimal_mark\":\",\",\"html_entity\":\"&#x20AC;\",\"iso_code\":\"EUR\",\"iso_numeric\":\"978\",\"name\":\"Euro\",\"smallest_denomination\":1,\"subunit\":\"Cent\",\"subunit_to_unit\":100,\"symbol\":\"\\u20ac\",\"symbol_first\":0,\"thousands_separator\":\".\"},\"flag\":\"\\ud83c\\uddf5\\ud83c\\uddf9\",\"geohash\":\"ez1z5quk85zunj9fwpg1\",\"qibla\":100.16,\"roadinfo\":{\"drive_on\":\"right\",\"road\":\"Avenida Doutor Louren\\u00e7o Peixinho\",\"speed_in\":\"km/h\"},\"sun\":{\"rise\":{\"apparent\":1620537900,\"astronomical\":1620531360,\"civil\":1620536100,\"nautical\":1620533820},\"set\":{\"apparent\":1620589080,\"astronomical\":1620595620,\"civil\":1620590880,\"nautical\":1620593160}},\"timezone\":{\"name\":\"Europe/Lisbon\",\"now_in_dst\":1,\"offset_sec\":3600,\"offset_string\":\"+0100\",\"short_name\":\"WEST\"},\"what3words\":{\"words\":\"investor.project.painters\"},\"wikidata\":\"Q800417\"},\"bounds\":{\"northeast\":{\"lat\":40.6433909,\"lng\":-8.6403431},\"southwest\":{\"lat\":40.6432909,\"lng\":-8.6404431}},\"components\":{\"ISO_3166-1_alpha-2\":\"PT\",\"ISO_3166-1_alpha-3\":\"PRT\",\"_category\":\"transportation\",\"_type\":\"railway\",\"city\":\"Aveiro\",\"continent\":\"Europe\",\"country\":\"Portugal\",\"country_code\":\"pt\",\"county\":\"Aveiro\",\"county_code\":\"AVR\",\"political_union\":\"European Union\",\"postcode\":\"3800-134\",\"railway\":\"Aveiro\",\"road\":\"Avenida Doutor Louren\\u00e7o Peixinho\",\"state\":\"Centro\",\"state_district\":\"Baixo Vouga\",\"suburb\":\"Vera Cruz\",\"village\":\"Esgueira\"},\"confidence\":9,\"formatted\":\"Aveiro, Avenida Doutor Louren\\u00e7o Peixinho, 3800-134 Aveiro, Portugal\",\"geometry\":{\"lat\":40.6433409,\"lng\":-8.6403931}},{\"annotations\":{\"DMS\":{\"lat\":\"40\\u00b0 38' 36.32172'' N\",\"lng\":\"8\\u00b0 38' 26.82060'' W\"},\"MGRS\":\"29TNE3037299236\",\"Maidenhead\":\"IN50qp34ck\",\"Mercator\":{\"x\":-961887.619,\"y\":4932050.534},\"OSM\":{\"edit_url\":\"https://www.openstreetmap.org/edit?node=1650269019#map=17/40.64342/-8.64078\",\"note_url\":\"https://www.openstreetmap.org/note/new#map=17/40.64342/-8.64078&layers=N\",\"url\":\"https://www.openstreetmap.org/?mlat=40.64342&mlon=-8.64078#map=17/40.64342/-8.64078\"},\"UN_M49\":{\"regions\":{\"EUROPE\":\"150\",\"PT\":\"620\",\"SOUTHERN_EUROPE\":\"039\",\"WORLD\":\"001\"},\"statistical_groupings\":[\"MEDC\"]},\"callingcode\":351,\"currency\":{\"alternate_symbols\":[],\"decimal_mark\":\",\",\"html_entity\":\"&#x20AC;\",\"iso_code\":\"EUR\",\"iso_numeric\":\"978\",\"name\":\"Euro\",\"smallest_denomination\":1,\"subunit\":\"Cent\",\"subunit_to_unit\":100,\"symbol\":\"\\u20ac\",\"symbol_first\":0,\"thousands_separator\":\".\"},\"flag\":\"\\ud83c\\uddf5\\ud83c\\uddf9\",\"geohash\":\"ez1z5qgvp5hu4u0jeyhr\",\"qibla\":100.16,\"roadinfo\":{\"drive_on\":\"right\",\"road\":\"Rua Doutor Arlindo Vicente\",\"speed_in\":\"km/h\"},\"sun\":{\"rise\":{\"apparent\":1620537900,\"astronomical\":1620531360,\"civil\":1620536100,\"nautical\":1620533820},\"set\":{\"apparent\":1620589080,\"astronomical\":1620595620,\"civil\":1620590880,\"nautical\":1620593160}},\"timezone\":{\"name\":\"Europe/Lisbon\",\"now_in_dst\":1,\"offset_sec\":3600,\"offset_string\":\"+0100\",\"short_name\":\"WEST\"},\"what3words\":{\"words\":\"object.outline.purist\"},\"wikidata\":\"Q800417\"},\"bounds\":{\"northeast\":{\"lat\":40.6434727,\"lng\":-8.6407335},\"southwest\":{\"lat\":40.6433727,\"lng\":-8.6408335}},\"components\":{\"ISO_3166-1_alpha-2\":\"PT\",\"ISO_3166-1_alpha-3\":\"PRT\",\"_category\":\"transportation\",\"_type\":\"railway\",\"city\":\"Aveiro\",\"city_district\":\"Gl\\u00f3ria e Vera Cruz\",\"continent\":\"Europe\",\"country\":\"Portugal\",\"country_code\":\"pt\",\"county\":\"Aveiro\",\"county_code\":\"AVR\",\"hamlet\":\"Forca\",\"political_union\":\"European Union\",\"postcode\":\"3800-163\",\"railway\":\"Aveiro\",\"road\":\"Rua Doutor Arlindo Vicente\",\"state\":\"Centro\",\"state_district\":\"Baixo Vouga\",\"suburb\":\"Vera Cruz\"},\"confidence\":9,\"formatted\":\"Aveiro, Rua Doutor Arlindo Vicente, 3800-163 Aveiro, Portugal\",\"geometry\":{\"lat\":40.6434227,\"lng\":-8.6407835}},{\"annotations\":{\"DMS\":{\"lat\":\"3\\u00b0 41' 32.40528'' S\",\"lng\":\"55\\u00b0 17' 4.47468'' W\"},\"MGRS\":\"21MXR9051891696\",\"Maidenhead\":\"GI26ih53uu\",\"Mercator\":{\"x\":-6154250.887,\"y\":-408563.913},\"OSM\":{\"edit_url\":\"https://www.openstreetmap.org/edit?relation=6275219#map=17/-3.69233/-55.28458\",\"note_url\":\"https://www.openstreetmap.org/note/new#map=17/-3.69233/-55.28458&layers=N\",\"url\":\"https://www.openstreetmap.org/?mlat=-3.69233&mlon=-55.28458#map=17/-3.69233/-55.28458\"},\"UN_M49\":{\"regions\":{\"AMERICAS\":\"019\",\"BR\":\"076\",\"LATIN_AMERICA\":\"419\",\"SOUTH_AMERICA\":\"005\",\"WORLD\":\"001\"},\"statistical_groupings\":[\"LEDC\"]},\"callingcode\":55,\"currency\":{\"decimal_mark\":\",\",\"html_entity\":\"R$\",\"iso_code\":\"BRL\",\"iso_numeric\":\"986\",\"name\":\"Brazilian Real\",\"smallest_denomination\":5,\"subunit\":\"Centavo\",\"subunit_to_unit\":100,\"symbol\":\"R$\",\"symbol_first\":1,\"thousands_separator\":\".\"},\"flag\":\"\\ud83c\\udde7\\ud83c\\uddf7\",\"geohash\":\"6z2dvzz484bj39n8x8xk\",\"qibla\":68.83,\"roadinfo\":{\"drive_on\":\"right\",\"speed_in\":\"km/h\"},\"sun\":{\"rise\":{\"apparent\":1620553200,\"astronomical\":1620548880,\"civil\":1620551880,\"nautical\":1620550380},\"set\":{\"apparent\":1620596100,\"astronomical\":1620600420,\"civil\":1620597420,\"nautical\":1620598920}},\"timezone\":{\"name\":\"America/Santarem\",\"now_in_dst\":0,\"offset_sec\":-10800,\"offset_string\":\"-0300\",\"short_name\":\"-03\"},\"what3words\":{\"words\":\"deciding.moorings.destiny\"}},\"bounds\":{\"northeast\":{\"lat\":-3.23236,\"lng\":-55.0373984},\"southwest\":{\"lat\":-4.1542282,\"lng\":-55.6052354}},\"components\":{\"ISO_3166-1_alpha-2\":\"BR\",\"ISO_3166-1_alpha-3\":\"BRA\",\"_category\":\"place\",\"_type\":\"neighbourhood\",\"city_district\":\"Aveiro\",\"continent\":\"South America\",\"country\":\"Brazil\",\"country_code\":\"br\",\"municipality\":\"Microrregi\\u00e3o de Itaituba\",\"region\":\"North Region\",\"state\":\"Par\\u00e1\",\"state_code\":\"PA\",\"state_district\":\"Regi\\u00e3o Geogr\\u00e1fica Intermedi\\u00e1ria de Santar\\u00e9m\",\"town\":\"Aveiro\"},\"confidence\":1,\"formatted\":\"Aveiro, Regi\\u00e3o Geogr\\u00e1fica Intermedi\\u00e1ria de Santar\\u00e9m, Brazil\",\"geometry\":{\"lat\":-3.6923348,\"lng\":-55.2845763}},{\"annotations\":{\"DMS\":{\"lat\":\"40\\u00b0 36' 46.21932'' N\",\"lng\":\"8\\u00b0 32' 39.12108'' W\"},\"MGRS\":\"29TNE3855695879\",\"Maidenhead\":\"IN50ro47qb\",\"Mercator\":{\"x\":-951136.021,\"y\":4927581.962},\"OSM\":{\"edit_url\":\"https://www.openstreetmap.org/edit?way=812083279#map=17/40.61284/-8.54420\",\"note_url\":\"https://www.openstreetmap.org/note/new#map=17/40.61284/-8.54420&layers=N\",\"url\":\"https://www.openstreetmap.org/?mlat=40.61284&mlon=-8.54420#map=17/40.61284/-8.54420\"},\"UN_M49\":{\"regions\":{\"EUROPE\":\"150\",\"PT\":\"620\",\"SOUTHERN_EUROPE\":\"039\",\"WORLD\":\"001\"},\"statistical_groupings\":[\"MEDC\"]},\"callingcode\":351,\"currency\":{\"alternate_symbols\":[],\"decimal_mark\":\",\",\"html_entity\":\"&#x20AC;\",\"iso_code\":\"EUR\",\"iso_numeric\":\"978\",\"name\":\"Euro\",\"smallest_denomination\":1,\"subunit\":\"Cent\",\"subunit_to_unit\":100,\"symbol\":\"\\u20ac\",\"symbol_first\":0,\"thousands_separator\":\".\"},\"flag\":\"\\ud83c\\uddf5\\ud83c\\uddf9\",\"geohash\":\"ez1zj966ctye2ygemdz5\",\"qibla\":100.21,\"roadinfo\":{\"drive_on\":\"right\",\"speed_in\":\"km/h\"},\"sun\":{\"rise\":{\"apparent\":1620537900,\"astronomical\":1620531360,\"civil\":1620536040,\"nautical\":1620533820},\"set\":{\"apparent\":1620589020,\"astronomical\":1620595620,\"civil\":1620590880,\"nautical\":1620593100}},\"timezone\":{\"name\":\"Europe/Lisbon\",\"now_in_dst\":1,\"offset_sec\":3600,\"offset_string\":\"+0100\",\"short_name\":\"WEST\"},\"what3words\":{\"words\":\"vast.slogged.rotation\"}},\"bounds\":{\"northeast\":{\"lat\":40.6131163,\"lng\":-8.5438505},\"southwest\":{\"lat\":40.612561,\"lng\":-8.54455}},\"components\":{\"ISO_3166-1_alpha-2\":\"PT\",\"ISO_3166-1_alpha-3\":\"PRT\",\"_category\":\"place\",\"_type\":\"village\",\"city\":\"Aveiro\",\"city_district\":\"Eixo e Eirol\",\"continent\":\"Europe\",\"country\":\"Portugal\",\"country_code\":\"pt\",\"county\":\"Aveiro\",\"county_code\":\"AVR\",\"industrial\":\"Aveiro\",\"political_union\":\"European Union\",\"state\":\"Centro\",\"state_district\":\"Baixo Vouga\",\"village\":\"Eirol\"},\"confidence\":9,\"formatted\":\"Eixo e Eirol, Aveiro, Portugal\",\"geometry\":{\"lat\":40.6128387,\"lng\":-8.5442003}},{\"annotations\":{\"DMS\":{\"lat\":\"40\\u00b0 38' 28.86072'' N\",\"lng\":\"8\\u00b0 35' 56.65884'' W\"},\"MGRS\":\"29TNE3390099022\",\"Maidenhead\":\"IN50qp83cw\",\"Mercator\":{\"x\":-957244.305,\"y\":4931747.659},\"OSM\":{\"edit_url\":\"https://www.openstreetmap.org/edit?way=812083275#map=17/40.64135/-8.59907\",\"note_url\":\"https://www.openstreetmap.org/note/new#map=17/40.64135/-8.59907&layers=N\",\"url\":\"https://www.openstreetmap.org/?mlat=40.64135&mlon=-8.59907#map=17/40.64135/-8.59907\"},\"UN_M49\":{\"regions\":{\"EUROPE\":\"150\",\"PT\":\"620\",\"SOUTHERN_EUROPE\":\"039\",\"WORLD\":\"001\"},\"statistical_groupings\":[\"MEDC\"]},\"callingcode\":351,\"currency\":{\"alternate_symbols\":[],\"decimal_mark\":\",\",\"html_entity\":\"&#x20AC;\",\"iso_code\":\"EUR\",\"iso_numeric\":\"978\",\"name\":\"Euro\",\"smallest_denomination\":1,\"subunit\":\"Cent\",\"subunit_to_unit\":100,\"symbol\":\"\\u20ac\",\"symbol_first\":0,\"thousands_separator\":\".\"},\"flag\":\"\\ud83c\\uddf5\\ud83c\\uddf9\",\"geohash\":\"ez1zhqd350g30mbwfp3g\",\"qibla\":100.19,\"roadinfo\":{\"drive_on\":\"right\",\"speed_in\":\"km/h\"},\"sun\":{\"rise\":{\"apparent\":1620537900,\"astronomical\":1620531360,\"civil\":1620536040,\"nautical\":1620533820},\"set\":{\"apparent\":1620589080,\"astronomical\":1620595620,\"civil\":1620590880,\"nautical\":1620593160}},\"timezone\":{\"name\":\"Europe/Lisbon\",\"now_in_dst\":1,\"offset_sec\":3600,\"offset_string\":\"+0100\",\"short_name\":\"WEST\"},\"what3words\":{\"words\":\"cheeks.rifts.tofu\"}},\"bounds\":{\"northeast\":{\"lat\":40.6416867,\"lng\":-8.5987731},\"southwest\":{\"lat\":40.6410136,\"lng\":-8.5993707}},\"components\":{\"ISO_3166-1_alpha-2\":\"PT\",\"ISO_3166-1_alpha-3\":\"PRT\",\"_category\":\"place\",\"_type\":\"village\",\"city\":\"Aveiro\",\"city_district\":\"Eixo e Eirol\",\"continent\":\"Europe\",\"country\":\"Portugal\",\"country_code\":\"pt\",\"county\":\"Aveiro\",\"county_code\":\"AVR\",\"industrial\":\"Aveiro\",\"political_union\":\"European Union\",\"state\":\"Centro\",\"state_district\":\"Baixo Vouga\",\"village\":\"Eixo\"},\"confidence\":9,\"formatted\":\"Eixo e Eirol, Aveiro, Portugal\",\"geometry\":{\"lat\":40.6413502,\"lng\":-8.5990719}}],\"status\":{\"code\":200,\"message\":\"OK\"},\"stay_informed\":{\"blog\":\"https://blog.opencagedata.com\",\"twitter\":\"https://twitter.com/OpenCage\"},\"thanks\":\"For using an OpenCage API\",\"timestamp\":{\"created_http\":\"Sun, 09 May 2021 20:26:36 GMT\",\"created_unix\":1620591996},\"total_results\":9}";

        Mockito.when(restTemplate.getForObject("https://api.opencagedata.com/geocode/v1/json?q=" + c1.getCity() + "&key=" + api2Key, String.class)).thenReturn(json);
        assertThat(airQualityGeoLocationService.getCoordsByCity(c1.getCity())).isEqualTo(c1);
    }

}
