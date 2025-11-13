# Light On – Dokumentáció

## Rövid leírás
A **Light On** egy egyszerű logikai játék, ahol a cél az, hogy **minden lámpát lekapcsoljunk** a 3×3-as rácson.  
- **Bekapcsolt lámpa**: sárga  
- **Lekapcsolt lámpa**: kék  

A játék **MVC (Model-View-Controller) struktúrában** készült:  
- **Model**: a játék logikáját és a lámpák állapotát kezeli  
- **View**: grafikus felület, a felhasználóval való interakciót biztosítja  
- **Controller**: összeköti a modellt és a nézetet, kezeli az eseményeket és frissíti a nézetet  

---
# Fájlstruktúra
'''

src/
├─ model/
│   └─ LampaModell.java
├─ nezet/
│   └─ GuiLampaNezet.java
├─ vezerlo/
│   └─ LampaVezerlo.java
├─ main/
│   └─ LightOn.java
└─ test/
    ├─ LampaVezerloTest.java
    ├─ GuiLampaNezetTest.java
    └─ LampaModellTest.java
'''
---

## 1. Modell (`LampaModell.java`)

**Tulajdonságok:**  
- `boolean[] lampak` – 9 lámpa állapota (`true` = lekapcsolt/kék, `false` = bekapcsolt/sárga)  
- `Random rnd` – véletlenszám-generátor új játékhoz  

**Fontos metódusok:**  
- `ujJatek()` – új játék generálása véletlenszerű állásokkal  
- `lampatValt(int index)` – a kiválasztott lámpa és a szomszédai állapotának megfordítása  
- `getAllapotok()` – az összes lámpa aktuális állapotának lekérése  
- `setAllapotok(boolean[] ujAllapot)` – az összes lámpa állapotának beállítása (pl. betöltéshez)  

---

## 2. Nézet (`GuiLampaNezet.java`)

**GUI elemek:**  
- 9 lámpa gomb (`btnLampa1` … `btnLampa9`)  
- `btnUjJatek` – új játék indítása  
- Menü:  
  - **Új játék** – új játék indítása  
  - **Mentés** – játék mentése  
  - **Betöltés** – korábbi mentett állapot betöltése  
  - **Kilépés** – kilépés megerősítéssel  
- `txtfKiiras` – információs üzenetek megjelenítése  

**Fontos metódusok:**  
- `addLampButtonListener(int index, ActionListener listener)` – kattintás kezelése lámpákhoz  
- `addUjJatekListener(ActionListener listener)` – új játék gomb kezelése  
- `addFajlbaMentListener(ActionListener listener)` – mentés menü item kezelése  
- `addBetoltFajlbolListener(ActionListener listener)` – betöltés menü item kezelése  
- `addKilepesListener(ActionListener listener)` – kilépés kezelése  
- `setLampColor(int index, Color color)` – lámpa színének frissítése  
- `setMessage(String msg)` – üzenet megjelenítése  

---

## 3. Vezérlő (`LampaVezerlo.java`)

**Funkciók:**  
- Összeköti a modellt és a nézetet  
- Kezeli az eseményeket:  
  - Lámpa kattintás → `lampatValt(int index)`  
  - Új játék → `ujJatek()`  
  - Mentés → fájlba menti a lámpák állapotát (`fajlbaMent()`)  
  - Betöltés → fájlból olvassa vissza az állást (`fajlbolBetolt()`)  
  - Kilépés → megerősítést kér (`kilepes_Megerosit()`)  
- Frissíti a lámpák színét (`frissitView()`)  
- Ellenőrzi a játék végét (`jatekVege()`)  

---

## Játék menete

1. Indítsd el a programot.  
2. A 3×3-as rácson a lámpák sárga (bekapcsolt) vagy kék (lekapcsolt) állapotban vannak.  
3. Kattints a lámpákra, hogy azok és szomszédaik állapota megváltozzon.  
4. Cél: **az összes lámpa lekapcsolása (kék)**.  
5. Új játék indítása mindig véletlenszerű kezdőállást hoz létre.  
6. A játék menthető és később betölthető.  

---

## Használt technológiák és eszközök

- Java SE 8+  
- Swing GUI  
- MVC tervezési minta  
- Fájlkezelés: `java.nio.file.Files`  

---

## Tesztelés (`LampaVezerloTest.java`)

- Ellenőrzi, hogy:  
  - Lámpa kattintás módosítja az állapotot  
  - Új játék új állapotot hoz létre  
  - Nézet frissítése nem dob hibát  
- Használ `assert`-eket a logika ellenőrzésére  
- Egyszerű, letisztult teszt, kiírásokkal minden lépéshez  

---

## Megjegyzés

- A játék logikája teljesen a **modellben** található.  
- A nézet "dumb", csak megjelenít, minden eseményt a vezérlő kezel.  
- Könnyen bővíthető új funkciókkal, például különböző méretű rácsokkal vagy színek variálásával.  
