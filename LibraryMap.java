package com.mycompany._please_return_your_books_on_time;

/**
 * The Grand Library Map: a two-dimensional array of section names (rows x columns).
 * It lives in its own class so that ExploreDesk (which shows the map) and BookSort
 * (which will ask "which section does this book belong to?") use the SAME sections.
 */
public class LibraryMap {

    // Row = one shelf row of the library, column = one section on that row.
    private final String[][] sections = {
        {"[01] Fiction & Literature",     "[02] Arcane & Mana Studies",       "[03] History & Cosmology"},
        {"[04] Bestiary & Monstrology",   "[05] Celestial Navigation",        "[06] Lost Languages & Runes"},
        {"[07] Philosophy & Ethics",      "[08] Forbidden & Cursed Texts",    "[09] Alchemy & Potions"},
        {"[10] Poetry & Song Archives",   "[11] Celestial & Astral Charts",   "[12] The Vault of Forgotten Knowledge"}
    };

    public int getRowCount() {
        return sections.length;
    }

    public int getColumnCount() {
        return sections[0].length;
    }

    /** Returns the section at the given row and column, or null if that spot is not on the map. */
    public String getSection(int row, int column) {
        if (row >= 0 && row < sections.length && column >= 0 && column < sections[row].length) {
            return sections[row][column];
        }
        return null;
    }

    /** Section numbers go 1-12, left to right, top to bottom. Returns null for a bad number. */
    public String getSectionByNumber(int number) {
        int columns = getColumnCount();
        if (number < 1 || number > getRowCount() * columns) {
            return null;
        }
        return sections[(number - 1) / columns][(number - 1) % columns];
    }

    /** Prints the whole map with nested loops over the 2D array, lining the columns up. */
    public void print() {
        int columns = getColumnCount();
        int[] widths = new int[columns];
        for (int row = 0; row < sections.length; row++) {
            for (int col = 0; col < columns; col++) {
                widths[col] = Math.max(widths[col], sections[row][col].length());
            }
        }
        for (int row = 0; row < sections.length; row++) {
            StringBuilder line = new StringBuilder("|  ");
            for (int col = 0; col < columns; col++) {
                line.append(String.format("%-" + (widths[col] + 3) + "s", sections[row][col]));
            }
            System.out.println(line.toString().stripTrailing());
        }
    }
}
