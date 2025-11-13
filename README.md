# Light On játék – Dokumentáció

## Rövid leírás
A **Light On** egy egyszerű logikai játék, ahol a cél az, hogy **minden lámpát lekapcsoljunk**. A játék 3x3-as rácson zajlik, minden cella egy lámpa, mely lehet **bekapcsolt (sárga)** vagy **lekapcsolt (kék)**.

A játék **MVC (Model-View-Controller) struktúrában** van felépítve:
- **Model**: a lámpák állapotát tárolja és kezeli a játék logikáját.
- **View**: a grafikus felület (GUI), a felhasználóval való interakciót biztosítja.
- **Controller**: összeköti a modellt és a nézetet, kezeli az eseményeket (kattintások, menüparancsok), és frissíti a nézetet.

---

## Fő komponensek

### 1. Modell (`LampaModell`)
- **Tulajdonságok**:
  - `boolean[] lampak` – a 9 lámpa állapota (`true` = lekapcsolt/kék, `false` = bekapcsolt/sárga)
- **Metódusok**:
  - `ujJatek()` – új játék generálása véletlenszerű állásokkal
  - `lampatValt(index)` – a kiválasztott lámpa és szomszédai állapotának megfordítása
  - `getAllapotok()` – az összes lámpa aktuális állapotának lekérése
  - `setAllapotok(boolean[] ujAllapot)` – az összes lámpa állapotának beállítása (pl. betöltéshez)

### 2. Nézet (`GuiLampaNezet`)
- **GUI elemek**:
  - 9 lámpa gomb (`btnLampa1` … `btnLampa9`)  
  - `btnUjJatek` – gomb új játék indításához  
  - Menü:
    - **Új játék** – új játék indítása
    - **Mentés** – játéktér elmentése
    - **Betöltés** – korábban mentett állás betöltése
    - **Kilépés** – kilépés megerősítéssel
  - `txtfKiiras` – információs üzenetek megjelenítése
- **Metódusok**:
  - `addLampButtonListener(index, listener)` – kattintás kezelése lámpákhoz
  - `addUjJatekListener(listener)` – új játék gomb kezelése
  - `addFajlbaMentListener(listener)` – mentés menü item kezelése
  - `addBetoltFajlbolListener(listener)` – betöltés menü item kezelése
  - `addKilepesListener(listener)` – kilépés kezelése
  - `setLampColor(index, Color)` – lámpa színének frissítése
  - `setMessage(String)` – üzenet megjelenítése

### 3. Vezérlő (`LampaVezerlo`)
- **Funkciók**:
  - Összeköti a modellt és a nézetet
  - Kezeli az eseményeket:
    - Lámpa kattintás → `lampatValt(index)`
    - Új játék → `ujJatek()`
    - Mentés → fájlba menti a lámpák állapotát
    - Betöltés → fájlból olvassa vissza az állást
    - Kilépés → megerősítést kér
  - Frissíti a lámpák színét (`frissitView()`)
  - Ellenőrzi a játék végét (`jatekVege()`)

---

## Menü és gomb funkciók
| Menü/Gomb          | Funkció                                                                 |
|-------------------|-----------------------------------------------------------------------|
| Új játék           | Új játék indítása véletlenszerű állással                               |
| Mentés             | Játéktér állapotának mentése szöveges fájlba                           |
| Betöltés           | Korábban mentett állapot betöltése                                      |
| Kilépés            | Kilépés megerősítéssel                                                 |
| Lámpa gombok       | Rákattintva a lámpa és szomszédai állapota megfordul (sárga/kék)      |

---

## Játék menete
1. Indítsd el a programot.
2. A 3x3-as rácson a lámpák sárga (bekapcsolt) vagy kék (lekapcsolt) állapotban vannak.
3. Kattints a lámpákra, hogy azok és szomszédaik állapota megváltozzon.
4. A cél: **az összes lámpa lekapcsolása (kék)**.
5. Új játék indítása mindig véletlenszerű kezdőállást hoz létre.
6. A játék menthető és később betölthető.

---

## Megjegyzés
- A program egyszerű, könnyen érthető és tisztán követi az **MVC elvet**.
- A logikai rész teljesen a modellben található, a nézet “dumb”, a vezérlő kezeli az összes eseményt és frissítést.
