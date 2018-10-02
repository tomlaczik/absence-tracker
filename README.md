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
