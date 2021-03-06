# Absence Tracker

## Funkcionális követelmények

- Vendég
  - regisztráció
  - bejelentkezés
- Hallgató
  - kijelentkezés
  - tárgyak böngészése
  - tanórák böngészése
  - tanóra felvétele
  - tanóra leadása
  - hiányzások megtekintése
- Tanár
  - kijelentkezés
  - saját tanórákra járó hallgatók megtekintése
  - hallgatók lejelentkeztetése saját tanóráról
  - hiányzás beírása
- Adminisztátor
  - kijelentkezés
  - felhasználó létrehozása
  - jogosultságok kiosztása
  - hallgatók számára tanóra felvétele/leadása
  - hiányzások kezelése
  - tantárgyak/tanórák létrehozása, törlése

## Nem-funkcionális követelmények

- letisztult design
- egyszerű kezelhetőség
- gyors
- hozzáférési jogosultságok megfelelő kezelése

## Fogalomjegyzék

- tantárgy: Egy egyetemi tárgy, mely alá egy vagy több tanóra tartozik.
- tanóra: Egy tantárgyhoz tartozó alkalom, mely lehet előadás, gyakorlat, illetve konzultációs is.
- hiányzás: Egy hallgató által felvett tantárgyhoz tartozó tanórán nem jelent meg.
- tanóra felvétele: Egy hallgató feljelentkezik egy tanórára.
- tanóra leadása: Egy hallgató lejelentkezik egy tanóráról.

## Szerepkörök

- Vendég: Az oldal felkeresésekor alapértelmezetten kiadott szerepkör; csak bejelentkezni, vagy regisztrálni tud.
- Hallgató: Bejelentkezett felhasználó, aki láthatja az órákat és saját hiányzásait.
- Tanár: Adminisztrátor által kiadott szerepkör; saját hallgatói hiányzásait tudja kezelni, illetve onnan tanulókat lejelentkeztetni.
- Adminisztrátor: Tantárgyak és tanórák létrehozása, tanárok kijelölése, minden adat módosítása.

## Fejlesztői Környezet / Használt Technológiák

- Fejlesztői környezet
  - NetBeans
- Használt technológiák:
  - Java SE
  - Spring Boot

## Adatbázis-terv

![alt text](https://github.com/tomlaczik/absence-tracker/blob/master/ERD.PNG)

## Könyvtárstruktúra

![alt text](https://github.com/tomlaczik/absence-tracker/blob/master/packages.PNG)

## Végpont leírások

### User Végpontok ~ mapping: "/users"

#### - "" @GetMapping
  - Kilistázza az összes usert.
  - Admin jogosultság szükséges.
#### - "/me" @GetMapping
  - A jelenleg authenticált usert adja vissza.
#### - "/id" @GetMapping
  - Kilistázza a usert, akihez tartozik az "id".
  - Admin vagy Teacher jogosultság szükséges
#### - /id/activeLessons @GetMapping
  - Kilistázza az "id"-vel rendelkező user felvett óráit
  - Admin jogosultság szükséges, vagy a keresett user
#### - /id/activeLessons @PostMapping
  - A megadott userhez a body-ban lévő tanórát felveszi.
  - Admin jogosultság szükséges, vagy az authentikált user
#### - /id/activeLessons @DeleteMapping
  - A megadott userhez a body-ban lévő tanórát leadja.
  - Admin jogosultság szükséges, vagy az authentikált user
#### - /id/taughtLessons @GetMapping
  - Kilistázza az "id"-vel rendelkező user tanított óráit
  - Admin jogosultság szükséges, vagy a keresett user
#### - /id/absences @GetMapping
  - Kilistázza az "id"-vel rendelkező user hiányzásait
  - Admin jogosultság szükséges, vagy a keresett user

### Lesson Végpontok ~ mapping: "/lessons"

#### - "" @GetMapping
  - Kilistázza az összes tanórát.
  - Nincs hozzáférés korlátozás.
#### - "/id" @GetMapping
  - Kilistázza a tanórát, amihez tartozik az "id".
  - Nincs hozzáférés korlátozás.
#### - "" @PostMapping
  - Új tanóra létrehozása
  - Admin jogosultság szükséges
#### - "/id" @PutMapping
  - Adatmódosítást hajt végre az "id"-vel rendelkező tanórán
  - Admin jogosultság szükséges
#### - "/id" @DeleteMapping
  - kitörli az "id"-vel rendelkező tanórát
  - Admin jogosultság szükséges
#### - "/id/absences" @GetMapping
  - Kilistázza az "id"-vel rendelkező tanórához tartozó hiányzásokat
  - Admin jogosultság vagy a tanóra tanárja szükséges hozzá
#### - "/id/students" @GetMapping
  - Kilistázza az "id"-vel rendelkező tanórához tartozó hallgatókat
  - Admin jogosultság vagy a tanóra tanárja szükséges hozzá
#### - "/id/absences" @PostMapping
  - Létrehoz egy hiányzást az "id"-vel rendelkező tanórához
  - Admin jogosultság vagy a tanóra tanárja szükséges hozzá
  
### Subject Végpontok ~ mapping: "/subjects"

#### - "" @GetMapping
  - Kilistázza az összes tantárgyat.
  - Nincs hozzáférés korlátozás.
#### - "/id" @GetMapping
  - Kilistázza a tantárgyat, amihez tartozik az "id".
  - Nincs hozzáférés korlátozás.
#### - "" @PostMapping
  - Új tantárgy létrehozása
  - Admin jogosultság szükséges
#### - "/id" @PutMapping
  - Adatmódosítást hajt végre az "id"-vel rendelkező tantárgyon
  - Admin jogosultság szükséges
#### - "/id" @DeleteMapping
  - kitörli az "id"-vel rendelkező tantárgyat
  - Admin jogosultság szükséges
#### - "/id/lessons" @GetMapping
  - Kilistázza az "id"-vel rendelkező tantárgyhoz tartozó tanórákat
  - Nincs hozzáférés korlátozás.
#### - "/id/lessons" @PostMapping
  - Létrehoz egy tanórát az "id"-vel rendelkező tantárgyhoz
  - Admin jogosultság szükséges hozzá
  
### Absence végpontok ~ mapping: "/absences"

#### - "" @GetMapping
  - Kilistázza az összes hiányzást.
  - Admin jogosultság szükséges
#### - "/id" @GetMapping
  - Kilistázza a hiányzást, amihez tartozik az "id".
  -  Admin jogosultság szükséges
#### - "" @PostMapping
  - Új hiányzás létrehozása
  - Admin jogosultság szükséges
#### - "/id" @PutMapping
  - Adatmódosítást hajt végre az "id"-vel rendelkező hiányzáson
  - Admin jogosultság szükséges
#### - "/id" @DeleteMapping
  - kitörli az "id"-vel rendelkező hiányzást
  - Admin jogosultság szükséges
  
 ### Subject Végpont részletes leírása
#### - ".../subjects/" @GetMapping
  - A végpontra belépve a függvény vissza adja az összes tantárgyat.
#### - ".../subjects/id" @ GetMapping
  - A végpontra belépve a függvény az "id" alapján megpróbálja megkeresni a tantárgyat. Ha megtalálja vissza tér ezzel, egyébként Not Found hibával tér vissza
#### - ".../subjects/" @PostMapping
  - A végpontra belépve a függvény ellenőrzi a belépett felhasználót, hogy rendelekzik-e ADMIN jogosultsággal, ha igen feltölti az új tantárgyat és ezzel tér vissza, egyébként Bad Request hibával tér vissza.
#### - ".../subjects/id" @PutMapping
  - A végpontra belépve a függvény ellenőrzi, hogy létezik-e a tantárgy, ha igen, ellenőrzi a jogosultságot. Ha a felhsználó ADMIN jogosultsággal rendelkezik, végrehajtja a módosítást és vissza tér az új értékkel, egyébként Not Foun hibát dob, ha nincs ilyen tanóra és Bad Request hibát ha rossz a jogosultság.
#### - ".../subjects/id" @DeleteMapping
  - A végpontra belépve a függvény ellenőrzi, hogy létezik-e a tantárgy, ha igen, ellenőrzi a jogosultságot. Ha a felhsználó ADMIN jogosultsággal rendelkezik, végrehajtja a törrlést és vissza ok-val, egyébként Not Found hibát dob, ha nincs ilyen tanóra és Bad Request hibát, ha rossz a jogosultság.
#### - ".../subjects/id/lessons" @GetMapping
  - A végpontra belépve a függvény ellenőrzi, hogy létezik-e a tantárgy, ha igen, vissza tér a tantárgy tanóráival, egyébként pedig Not Found hibával.
#### - ".../subjects/id/lessons" @PostMapping
  -  A végpontra belépve a függvény ellenőrzi, hogy létezik-e a tantárgy, ha igen, ellenőrzi a jogosultságot. Ha a felhsználó ADMIN jogosultsággal rendelkezik, végrehajtja az új tanóra létrehozását és vissza tér ezzel, egyébként Not Found hibát dob, ha nincs ilyen tanóra és Bad Request hibát ha rossz a jogosultság.
