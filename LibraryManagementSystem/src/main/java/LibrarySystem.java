/**
 * Assignment:      Formative Assessment 2 (Knowledge Module 02)
 * Student Name:    Teboho Katleho Emmanuel Monaheng
 * Student Number:  20251475
 * Course:          Occupational Certificate: Software Engineer (Level 1 of 2)
 * Institution:     CTU Training Solutions
 *
 * Program details:
 * Library Management System in Java
 *
 * @author Taay Sosa
 * @since 2025-09-09
 */

// Import statement that brings all the classes and interfaces into the program.
import java.util.*;

/**
 * Abstract base class that represents a generic book in the library system.
 * Demonstrates encapsulation with private fields and accessors/mutators.
 * Provides polymorphism through abstract displayDetails() and overridable calculateLateFee().
 */
abstract class Book {
    // Private fields for encapsulation.
    private int id;
    private String title;
    private String author;
    private int yearPublished;
    private String category;

    /**
     ** Constructor to initialize the Book object.
     * @param id                Unique ID.
     * @param title             Book title (validated with a setter).
     * @param author            Book author (validated with a setter).
     * @param yearPublished     Publication year (validated with a setter).
     * @param category          Book category/genre (defaults to "General").
     */
    public Book(int id, String title, String author, int yearPublished, String category) {
        // Unique 3-digit identifier for the book (auto-generated).
        this.id = id;
        // Uses setters for validation.
        this.setTitle(title);
        this.setAuthor(author);
        this.setYearPublished(yearPublished);
        // Category/genre of the book (e.g., Thriller, Fiction).
        // Defaults to "General", if null or empty.
        this.category = category;
    }

    /**
     * Getters (mutators) for encapsulation.
     * Return: The book's: ID, title, author, publication year, category,
     *          true if the book is available, false otherwise.
     */
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYearPublished() { return yearPublished; }
    public String getCategory() { return category; }

    // Setters (mutators) with basic validation.
    /**
     * Sets the book's title with validation.
     * @param title The book's title.
     * @throws IllegalArgumentException if title is null or empty.
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        } else {
            this.title = title;
        }
    }
    /**
     * Sets the book's title with validation.
     * @param author The book's author.
     * @throws IllegalArgumentException if author is null or empty.
     */
    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        } else {
            this.author = author;
        }
    }
    /**
     * Sets the book's publication year with validation.
     * @param yearPublished The book's publication year.
     * @throws IllegalArgumentException if publication year is null or empty.
     */
    public void setYearPublished(int yearPublished) {
        if (yearPublished < 0 || yearPublished > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new IllegalArgumentException("Invalid year published");
        } else {
            this.yearPublished = yearPublished;
        }
    }
    /**
     * Sets the book's category and defaults to "General".
     * @param yearPublished The book's publication year.
     * @throws IllegalArgumentException if publication year is null or empty.
     */
    public void setCategory(String category) {
        if (category != null && !category.isEmpty()) {
            this.category = category;
        } else {
            this.category = "General";
        }
    }

    /**
     * Abstract method to display the books details.
     * Each subclass provide its own implementation (overridden in subclasses for polymorphism).
     */
    public abstract void displayDetails();
    public abstract void displayDetailsBlock();

    /**
     * Abstract method that calculates late fee based on days late. Base rate: $0.50 per day.
     * Each subclass provide its own implementation (overridable for subclass-specific rates).
     *
     * @param daysLate Number of days late.
     * @return Calculated late fee.
     */
    public double calculateLateFee(int daysLate) {
        if (daysLate < 0) return 0;
        return daysLate * 0.5;
    }

    public abstract double calculateLateFees(int daysLate);
}

/**
 * Subclass for electronic books, that extends from Book (base class).
 * Demonstrates inheritance and method overriding.
 * Adds format and file size attributes.
 * Overrides displayDetails() and calculateLateFee() (no late fee for electronic books).
 */
class EBook extends Book {
    // EBooks file format (e.g., PDF, EPUB) and file size in MB.
    private String format;
    private double fileSize;

    public static final String INDENTOR = " ".repeat(5);

    /**
     * Constructor for EBooks.
     * @param id                Unique ID.
     * @param title             EBooks' title (validated with a setter).
     * @param author            EBooks' author (validated with a setter).
     * @param yearPublished     Publication year (validated with a setter).
     * @param category          EBooks' category/genre (defaults to "General").
     * @param format            EBooks' file format (e.g., PDF, EPUB).
     * @param fileSize          EBooks' file size in MB.
     */
    public EBook(int id, String title, String author, int yearPublished, String category, String format,
                 double fileSize) {
        super(id, title, author, yearPublished, category);
        this.setFormat(format);
        this.setFileSize(fileSize);
    }

    /**
     * Getters for EBooks.
     * Return The eBook's format (e.g., PDF) and file size (in MB).
     */
    public String getFormat() { return format; }
    public double getFileSize() { return fileSize; }

    /**
     * Sets the EBook's format and defaults to "TXT".
     * @param title The book's title.
     * @throws IllegalArgumentException if title is null or empty.
     */
    public void setFormat(String format) {
        if (format != null && !format.isEmpty()) {
            this.format = format;
        } else {
            this.format = "TXT";
        }
    }
    /**
     * Sets the EBook's file size with validation.
     * @param title The book's title.
     * @throws IllegalArgumentException if title is null or empty.
     */
    public void setFileSize(double fileSize) {
        if (fileSize > 0) {
            this.fileSize = fileSize;
        } else {
            throw new IllegalArgumentException("File size must be positive");
        }
    }

    /**
     * Polymorphic method implementation.
     * Demonstrates dynamic method dispatch.
     */
    @Override
    public void displayDetails() {
        System.out.printf("%-12s %-5s %-45s %-20s %-5s %-15s %-15s %-10s%n", "PrintedBook", getId(), getTitle(),
                getAuthor(), getYearPublished(), getCategory(), format, fileSize + ".MB");
    }

    /**
     * Another way of display a books details (inline details).
     */
    @Override
    public void displayDetailsBlock() {
        System.out.println("   ~~~ EBOOK DETAILS ~~~");
        System.out.printf(INDENTOR + "ID: %d%n", getId());
        System.out.printf(INDENTOR + "Title: %s%n", getTitle());
        System.out.printf(INDENTOR + "Author: %s%n", getAuthor());
        System.out.printf(INDENTOR + "Year: %d%n", getYearPublished());
        System.out.printf(INDENTOR + "Category: %s%n", getCategory());
        System.out.printf(INDENTOR + "File Size: %.2f MB%n", fileSize);
        System.out.printf(INDENTOR + "Format: %s%n", format);
    }

    /**
     * Calculates late fee based on days late. Base rate: $0.50 per day.
     * No late fees for digital books.
     * @param daysLate Number of days late.
     * @return Calculated late fee.
     */
    @Override
    public double calculateLateFees(int daysLate) {
        return super.calculateLateFee(daysLate) * 0;
    }
}

/**
 * Subclass for printed books, extends from Book (base class).
 * Demonstrates inheritance and method overriding.
 * Adds pages, ISBN (auto-generated), and publisher.
 * Overrides displayDetails() and calculateLateFee() (base rate).
 */
class PrintedBook extends Book {
    // Printed books': Number of pages, ISBN, Publisher.
    private int pages;
    private String isbn;
    private String publisher;

    public static final String INDENTOR = " ".repeat(5);

    /**
     * Constructor for PrintedBook (auto-generates ISBN).
     * @param id                Unique ID.
     * @param title             Physical books' title (validated with a setter).
     * @param author            Physical books' author (validated with a setter).
     * @param yearPublished     Publication year (validated with a setter).
     * @param category          Physical books' category/genre (defaults to "General").
     * @param pages             Physical books' number of pages.
     * @param publisher         Physical books' publisher.
     */
    public PrintedBook(int id, String title, String author, int yearPublished, String category, int pages,
                       String publisher) {
        super(id, title, author, yearPublished, category);
        this.setPages(pages);
        this.setPublisher(publisher);
        this.isbn = generateISBN();
    }

    /**
     * Generates a random 12-char ISBN starting with "213-b-".
     * @return Generated ISBN.
     */
    private String generateISBN() {
        String prefix = "213-b-";
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = 0; i < 6; i++) {
            // Total 12 chars: 6 prefix + 6 random
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /**
     * Getters for PrintedBooks.
     * @return The Physical book's: number of pages, ISBN, publisher.
     */
    public int getPages() { return pages; }
    public String getIsbn() { return isbn; }
    public String getPublisher() { return publisher; }

    /**
     * Sets the PrintedBook's pages with validation.
     * @param title The book's title.
     * @throws IllegalArgumentException if title is null or empty.
     */
    public void setPages(int pages) {
        if (pages > 0) {
            this.pages = pages;
        } else {
            throw new IllegalArgumentException("Pages must be positive");
        }
    }
    /**
     * Sets the PrintedBook's publisher with validation.
     * @param title The book's title.
     * @throws IllegalArgumentException if title is null or empty.
     */
    public void setPublisher(String publisher) {
        if (publisher != null && !publisher.isEmpty()) {
            this.publisher = publisher;
        } else {
            throw new IllegalArgumentException("Publisher cannot be empty");
        }
    }

    /**
     * Polymorphic method implementation.
     * Demonstrates dynamic method dispatch.
     */
    @Override
    public void displayDetails() {
        System.out.printf("%-12s %-5s %-45s %-20s %-5s %-15s %-15s %-15s %-30s%n", "PrintedBook", getId(), getTitle(),
                getAuthor(), getYearPublished(), getCategory(), isbn , pages , publisher);
    }

    /**
     * Another way of display a books details (inline details).
     */
    @Override
    public void displayDetailsBlock() {
        System.out.println("  ~~~ PRINTED BOOK DETAILS ~~~");
        System.out.printf(INDENTOR + "ID: %d%n", getId());
        System.out.printf(INDENTOR + "Title: %s%n", getTitle());
        System.out.printf(INDENTOR + "Author: %s%n", getAuthor());
        System.out.printf(INDENTOR + "Year: %d%n", getYearPublished());
        System.out.printf(INDENTOR + "Category: %s%n", getCategory());
        System.out.printf(INDENTOR + "Copies: %d%n", pages);
        System.out.printf(INDENTOR + "ISBN: %s%n", isbn);
        System.out.printf(INDENTOR + "Publisher: %s%n", publisher);
        //System.out.printf("Status: %s%n", isAvailable() ? "Available for Download" : "License Limit Reached");
        //System.out.printf("Download URL: %s%n", downloadUrl != null ? downloadUrl : "N/A");
    }

    /**
     * Calculates late fee based on days late. Base rate: $0.50 per day.
     * Printed books late fee ($0.50 per day late, max $25.00).
     * @param daysLate Number of days late.
     * @return Calculated late fee.
     */
    @Override
    public double calculateLateFees(int daysLate) {
        if (daysLate <= 0) return 0.0;
        return Math.min(daysLate * 0.50, 25.00);
    }

}

/**
 * Manages undo/redo actions for book additions using two Stacks (LIFO).
 * Demonstrates Stack usage from Collections framework.
 */
class UndoRedoManager {
    /**
     * Private fields for encapsulation.
     * - undoStack:   Stack for undo actions (recent additions).
     * - redoStack:   Stack for redo actions (undone additions).
     */
    private Stack<Book> undoStack = new Stack<>();
    private Stack<Book> redoStack = new Stack<>();

    /**
     * Adds the newly added book (action) to the undo stack and clears redo.
     * @param book The book added.
     */
    public void addAction(Book book) {
        undoStack.push(book);
        redoStack.clear();
    }

    /**
     * Removes the recently removed book (action) from the undo stack and clears redo.
     * @param book The book added.
     */
    public void removeAction(Book book) {
        undoStack.remove(book);
        redoStack.clear();
    }

    /**
     * Undoes/Removes the last addition/book, removes from list/library, pushes to redo.
     * @param books The library's book list.
     * @return The undone book, or null if none.
     */
    public Book undo(List<Book> books) {
        // Checks that the stack is not empty before performing the action.
        if (!undoStack.isEmpty()) {
            Book lastBook = undoStack.pop();
            books.remove(lastBook);
            redoStack.push(lastBook);
            return lastBook;
        }
        // returns nothing if the stack is empty (no last added books).
        return null;
    }

    /**
     * Redoes/Re-Adds the last undo/removed book, adds back to list/library, pushes to undo.
     * @param books The library's book list.
     * @return The redone book, or null if none.
     */
    public Book redo(List<Book> books) {
        // Checks that the stack is not empty before performing the action.
        if (!redoStack.isEmpty()) {
            Book redoBook = redoStack.pop();
            books.add(redoBook);
            undoStack.push(redoBook);
            return redoBook;
        }
        // returns nothing if the stack is empty (no last removed books).
        return null;
    }

}

/**
 * Manages sorting and searching algorithms:
 * - Implements custom Merge Sort (stable) and Quick Sort (in-place).
 * - Binary Search requires sorted list.
 * - Includes performance timing for comparison.
 */
class SortSearchManager {
    /**
     * Sorts the list using Merge Sort (recursive divide-and-conquer).
     * @param bookz List to sort (library books).
     * @param left Start index (books).
     * @param right End index (books).
     */
    public void mergeSort(List<Book> books, int left, int right) {
        // Checks that the library is not empty before sorting the books.
        if (!books.isEmpty()) {
            //
            if (left < right) {
                int mid = left + (right - left) / 2;
                mergeSort(books, left, mid);
                mergeSort(books, mid + 1, right);
                merge(books, left, mid, right);
            }
            // If the library does not contain any books.
        } else {
            System.out.println("There library currently has no books");
        }
    }

    /**
     * Helper method to merge two halves in Merge Sort.
     * Compares titles ignore-case.
     * @param bookz List of library books.
     * @param left Start index (books).
     * @param mid Midpoint index (books).
     * @param right End index (books).
     */
    private void merge(List<Book> books, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        // Array list to.
        List<Book> L = new ArrayList<>(n1);
        List<Book> R = new ArrayList<>(n2);
        for (int i = 0; i < n1; ++i) { L.add(books.get(left + i)); }
        for (int j = 0; j < n2; ++j) { R.add(books.get(mid + 1 + j)); }
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L.get(i).getTitle().compareToIgnoreCase(R.get(j).getTitle()) <= 0) {
                books.set(k++, L.get(i++));
            } else {
                books.set(k++, R.get(j++));
            }
        }
        while (i < n1) books.set(k++, L.get(i++));
        while (j < n2) books.set(k++, R.get(j++));
    }

    /**
     * Sorts the list using Quick Sort (recursive partitioning).
     * @param arr List to sort (library books).
     * @param low Start index (books).
     * @param high End index (books).
     */
    public void quickSort(List<Book> books, int low, int high) {
        if (!books.isEmpty()) {
            if (low < high) {
                int pi = partition(books, low, high);
                quickSort(books, low, pi - 1);
                quickSort(books, pi + 1, high);
            }
            // If the library does not contain any books.
        } else {
            System.out.println("There library currently has no books");
        }

    }

    /**
     * Helper for Quick Sort: Partitions around pivot (last element's title).
     * @param arr List of library books.
     * @param low Start index (books).
     * @param high End index (books).
     * @return Partition index (Midpoint index of the books).
     */
    private int partition(List<Book> books, int low, int high) {
        String pivot = books.get(high).getTitle();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (books.get(j).getTitle().compareToIgnoreCase(pivot) < 0) {
                i++;
                Collections.swap(books, i, j);
            }
        }
        Collections.swap(books, i + 1, high);
        return i + 1;
    }

    /**
     * Performs binary search on the sorted list (library books) by title.
     * @param books Sorted list.
     * @param title Title/Keyword to search.
     * @return Found Book or null.
     */
    public Book binarySearch(List<Book> books, String title) {
        int low = 0;
        int high = books.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = books.get(mid).getTitle().compareToIgnoreCase(title);
            if (cmp == 0) {
                return books.get(mid);
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    /**
     * Sorts the list (library books) with given algorithm and times it for performance comparison.
     * Updates the original list with the sorted copy.
     * @param books List to sort.
     * @param algo "merge" or "quick".
     */
    public void sortListByTitle(List<Book> books, String algo) {
        List<Book> copy = new ArrayList<>(books);
        // Checks which sorting algorithm was chosen.
        if ("merge".equals(algo)) {
            mergeSort(copy, 0, copy.size() - 1);
        } else if ("quick".equals(algo)) {
            quickSort(copy, 0, copy.size() - 1);
        }
        // Updates the original list with the sorted copy.
        books.clear();
        books.addAll(copy);
    }
}

/**
 * User interface class that handles user interaction via console menu.
 * Includes input validation and exception handling.
 */
class UserInterface {
    /**
     * Private fields for encapsulation:
     * - Scanner:         For user input.
     * - LibrarySystem:   Reference to the library system.
     */
    private final Scanner scanner = new Scanner(System.in);
    private final LibrarySystem library;
    //
    public static final String INDENTOR = " ".repeat(5);

    /**
     * Constructor.
     * @param library The Library Management System instance.
     */
    public UserInterface(LibrarySystem library) { this.library = library; }

    /**
     * Displays welcome message and program information.
     */
    public static void menuStartUp() {
        System.out.println("\nStarting Library Management System:\n");
        System.out.println("=".repeat(50));
        System.out.println(INDENTOR + INDENTOR + "Library Management System");
        System.out.println("Add, Remove, Sort And Search Books In The Library");
        System.out.println("=".repeat(50));
    }

    /**
     * Displays closing message for the program.
     */
    public static void closeMenu() {
        System.out.println("-".repeat(40) + "\n");
        System.out.println("Closing Library Management System:");
        System.out.println("Thank you for using Library Management System!");
        System.out.println("=".repeat(50));
        System.exit(0);
    }

    /**
     * Sub-menu on the library menu for managing the library's books (add, remove, edit) choice.
     */
    public void manageLibraryMenu() {
        boolean managing = true;

        while (managing) {
            System.out.println("   ----- Manage Library: -----");
            System.out.println(INDENTOR + "1. Add Book");
            System.out.println(INDENTOR + "2. Remove Book");
            System.out.println(INDENTOR + "3. Edit Book Details");
            System.out.println(INDENTOR + "4. Back");
            System.out.println("-".repeat(40));
            System.out.print(INDENTOR + "Choose option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("-".repeat(40) + "\n");
                        library.addBookByInput(scanner);
                        break;
                    case 2:
                        System.out.println("-".repeat(40) + "\n");
                        library.removeBookByInput(scanner);
                        break;
                    case 3:
                        System.out.println("-".repeat(40) + "\n");
                        library.editBookByInput(scanner);
                        break;
                    case 4:
                        System.out.println("-".repeat(40) + "\n");
                        runMenu();
                        managing = false;
                        break;
                    default:
                        System.out.println("-".repeat(40) + "\n");
                        System.out.println("Invalid option. Try again.");
                        System.out.println("=".repeat(50) + "\n");
                }
                // Catches invalid user inputs regarding the library's sorting options menu.
            } catch (NumberFormatException e) {
                System.out.println("-".repeat(40));
                System.out.println("Invalid input: Please enter a number.");
                System.out.println("=".repeat(50) + "\n");
            } catch (Exception e) {
                System.out.println("-".repeat(40));
                System.out.println("Error: " + e.getMessage());
                System.out.println("=".repeat(50) + "\n");
            }
        }
    }

    /**
     * Sub-menu on the library menu for the sorting algorithms choice.
     */
    public void sortOptionsMenu() {
        boolean sorting = true;

        while (sorting) {
            System.out.println("   ------ Sorting Options: ------");
            System.out.println(INDENTOR + "1. Merge Sort");
            System.out.println(INDENTOR + "2. Quick Sort");
            System.out.println(INDENTOR + "3. Compare Sort Methods");
            System.out.println(INDENTOR + "4. Back");
            System.out.println("-".repeat(40));
            System.out.print(INDENTOR + "Choose option: ");

            try {
                int sortChoice = Integer.parseInt(scanner.nextLine());
                switch (sortChoice) {
                    case 1:
                        System.out.println("-".repeat(40) + "\n");
                        library.sortListByTitle("merge");
                        break;
                    case 2:
                        System.out.println("-".repeat(40) + "\n");
                        library.sortListByTitle("quick");
                        break;
                    case 3:
                        System.out.println("-".repeat(40) + "\n");
                        library.compareSortPerformances();
                        break;
                    case 4:
                        System.out.println("-".repeat(40) + "\n");
                        sorting = false;
                        runMenu();
                        break;
                    default:
                        System.out.println("-".repeat(40) + "\n");
                        System.out.println("Invalid option. Try again.");
                        System.out.println("=".repeat(50) + "\n");
                }
                // Catches invalid user inputs regarding the library's sorting options menu.
            } catch (NumberFormatException e) {
                System.out.println("-".repeat(40));
                System.out.println("Invalid input: Please enter a number.");
                System.out.println("=".repeat(50) + "\n");
            } catch (Exception e) {
                System.out.println("-".repeat(40));
                System.out.println("Error: " + e.getMessage());
                System.out.println("=".repeat(50) + "\n");
            }
        }
    }

    /**
     * Sub-menu on the library menu for viewing the entire library's books.
     */
    public void catalogMenu() {
        boolean catalog = true;

        while (catalog) {
            System.out.println("   ------ Library Catalog: ------");
            System.out.println(INDENTOR + "1. Display All Books");
            System.out.println(INDENTOR + "2. Show Books by Category");
            System.out.println(INDENTOR + "3. Back");
            System.out.println("-".repeat(40));
            System.out.print(INDENTOR + "Choose option: ");

            try {
                int sortChoice = Integer.parseInt(scanner.nextLine());
                switch (sortChoice) {
                    case 1:
                        System.out.println("-".repeat(40) + "\n");
                        library.displayAllBooks();
                        break;
                    case 2:
                        System.out.println("-".repeat(40) + "\n");
                        library.displayBooksByCategory();
                        break;
                    case 3:
                        System.out.println("-".repeat(40) + "\n");
                        catalog = false;
                        runMenu();
                        break;
                    default:
                        System.out.println("-".repeat(40) + "\n");
                        System.out.println("Invalid option. Try again.");
                        System.out.println("=".repeat(50) + "\n");
                }
                // Catches invalid user inputs regarding the library's sorting options menu.
            } catch (NumberFormatException e) {
                System.out.println("-".repeat(40));
                System.out.println("Invalid input: Please enter a number.");
                System.out.println("=".repeat(50) + "\n");
            } catch (Exception e) {
                System.out.println("-".repeat(40));
                System.out.println("Error: " + e.getMessage());
                System.out.println("=".repeat(50) + "\n");
            }
        }
    }

    /**
     * Runs the interactive menu (loop), until the program is closed/exited.
     */
    public void runMenu() {
        boolean running = true;
        // Displays the library's UI while the system is running/(loop is true).
        while (running) {
            System.out.println("   ------- Library Menu: --------");
            System.out.println(INDENTOR + "1. Manage Library");
            System.out.println(INDENTOR + "2. Sort Books by Title");
            System.out.println(INDENTOR + "3. Search Book by Title");
            System.out.println(INDENTOR + "4. Undo Last Addition");
            System.out.println(INDENTOR + "5. Redo Last Undo Action");
            System.out.println(INDENTOR + "6. Library Catalog");
            System.out.println(INDENTOR + "7. Calculate Late Fee for a Book");
            System.out.println(INDENTOR + "8. Recommend a Random Book");
            System.out.println(INDENTOR + "9. Exit");
            System.out.println("-".repeat(40));
            System.out.print(INDENTOR + "Choose option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("-".repeat(40) + "\n");
                        manageLibraryMenu();
                        break;
                    case 2:
                        System.out.println("-".repeat(40) + "\n");
                        sortOptionsMenu();
                        break;
                    case 3:
                        System.out.println("-".repeat(40) + "\n");
                        library.searchByInput(scanner);
                        break;
                    case 4:
                        System.out.println("-".repeat(40) + "\n");
                        library.undo();
                        break;
                    case 5:
                        System.out.println("-".repeat(40) + "\n");
                        library.redo();
                        break;
                    case 6:
                        System.out.println("-".repeat(40) + "\n");
                        catalogMenu();
                        break;
                    case 7:
                        System.out.println("-".repeat(40) + "\n");
                        library.calculateLateFeeByInput(scanner);
                        break;
                    case 8:
                        System.out.println("-".repeat(40) + "\n");
                        library.recommendRandomBook();
                        break;
                    case 9:
                        closeMenu();
                        running = false;
                        break;
                    default:
                        System.out.println("-".repeat(40) + "\n");
                        System.out.println("Invalid option. Try again.");
                        System.out.println("=".repeat(50) + "\n");
                }
                // Catches invalid user inputs regarding the library's menu.
            } catch (NumberFormatException e) {
                System.out.println("-".repeat(40));
                System.out.println("Invalid input: Please enter a number.");
                System.out.println("=".repeat(50) + "\n");
            } catch (Exception e) {
                System.out.println("-".repeat(40));
                System.out.println("Error: " + e.getMessage());
                System.out.println("=".repeat(50) + "\n");
            }
        }
    }

}

/**
 * Main class coordinating the library system.
 * Uses ArrayList for book storage (efficient random access).
 * Auto-generates 3-digit IDs.
 */
public class LibrarySystem {
    /**
     * Private fields for encapsulation:
     *      - ArrayList:         List of books.
     *      - UndoRedoManager:   Manager for undo/redo algorithms/features.
     *      - SortSearchManager: Manager for sorting/searching algorithms/features.
     *      - Random:            Random instance for recommendations.
     */
    private List<Book> books = new ArrayList<>();
    private UndoRedoManager undoRedoManager = new UndoRedoManager();
    private SortSearchManager sortSearchManager = new SortSearchManager();
    private Random random = new Random();
    private int nextID = 210;

    public static final String INDENTOR = " ".repeat(5);

    /**
     * Getters (mutators) for the list and (algorithmic) managers.
     * Returns: The book list, The undo/redo manager and The sort/search manager.
     */
    public List<Book> getBooks() { return books; }
    public UndoRedoManager getUndoRedoManager() { return undoRedoManager; }
    public SortSearchManager getSortSearchManager() { return sortSearchManager; }

    /**
     * Adds books to the list/library and records action for undo.
     * @param book The book to add.
     */
    public void addBook(Book book) {
        books.add(book);
        undoRedoManager.addAction(book);
        System.out.println("Added: ");
        System.out.println("-".repeat(40));
        book.displayDetailsBlock();
        System.out.println("-".repeat(40));
        System.out.println("Book was added successfully");
        System.out.println("=".repeat(50) + "\n");
    }

    /**
     * Removes books from the list/library and records action for undo.
     * @param book The book to remove.
     * @param scanner Input scanner to enter the book.
     */
    public void removeBook(Book book) {
        books.remove(book);
        undoRedoManager.removeAction(book);
        System.out.println("Removed: ");
        System.out.println("-".repeat(40));
        book.displayDetailsBlock();
        System.out.println("-".repeat(40));
        System.out.println("Book was removed successfully");
        System.out.println("=".repeat(50) + "\n");
    }

    /**
     * Undoes/Removes the last book addition.
     * Utilizes the undo/redo manager to
     */
    public void undo() {
        Book undone = undoRedoManager.undo(books);

        if (undone != null) {
            System.out.println("Removed book: ");
            System.out.println("-".repeat(40));
            undone.displayDetailsBlock();
            System.out.println("-".repeat(40));
            System.out.println("Book was removed successfully");
            System.out.println("=".repeat(50)+ "\n");
        } else {
            System.out.println("No actions/books to remove");
            System.out.println("=".repeat(50)+ "\n");
        }
    }

    /**
     * Redoes/Re-adds the last book addition.
     * Utilizes the undo/redo manager to
     */
    public void redo() {
        Book redone = undoRedoManager.redo(books);

        if (redone != null) {
            System.out.println("Re-Added removed book: ");
            System.out.println("-".repeat(40));
            redone.displayDetailsBlock();
            System.out.println("-".repeat(40));
            System.out.println("Book was re-added successfully");
            System.out.println("=".repeat(50) + "\n");
        } else {
            System.out.println("No actions/books to add");
            System.out.println("=".repeat(50) + "\n");
        }
    }

    /**
     * Displays the library's entire catalog.
     * Utilizes the undo/redo manager to
     */
    public void displayAllBooks() {
        // Checks that the book list is not empty.
        if (books.isEmpty()) {
            System.out.println("There are currently no books in the library");
            System.out.println("=".repeat(50) + "\n");
            return;
        }
        // Sorts list ensure the books are displayed in order.
        sortSearchManager.sortListByTitle(books, "merge");

        System.out.println(INDENTOR + "~~~ Library's Books: ~~~");
        System.out.println(INDENTOR + "Total Books: " + books.size() + "\n");
        // Loops (iterates) through the book list, displaying each book (and its details).
        System.out.printf("%-12s %-5s %-45s %-20s %-5s %-15s %-15s %-10s %-30s%n", "Book Type", "[ID]", "Title",
                "Author", "Year", "Category", "ISBN / Form", "Pg. / File", "Publisher\n");
        for (Book book : books) {
            book.displayDetails();
        }
        System.out.println("=".repeat(50) + "\n");
    }

    /**
     * Sorts books (list) by title and times the operation.
     * Utilizes the sort/search manager to
     * @param algo Sort algorithm being implemented/timed (either "merge" or "quick").
     */
    public void sortListByTitle(String algo) {
        sortSearchManager.sortListByTitle(books, algo);
        System.out.println("-".repeat(40));
        System.out.println("Library books have been sorted successfully");
        System.out.println("=".repeat(50) + "\n");
    }

    /**
     * Compares the performance of Merge Sort and Quick Sort on copies of the current book list.
     */
    public void compareSortPerformances() {
        if (books.isEmpty()) {
            System.out.println("There are currently  no books to sort.");
            return;
        }
        System.out.println("Comparing sort performances on current library size: " + books.size());
        System.out.printf("%-5s %-15s %-15s%n", "", "Sorting Method", "Time");

        // Time Merge Sort
        List<Book> copy1 = new ArrayList<>(books);
        long start = System.nanoTime();
        sortSearchManager.mergeSort(copy1, 0, copy1.size() - 1);
        long end = System.nanoTime();
        // System.out.println("Merge Sort Time: " + (end - start) / 1_000_000.0 + " ms");
        System.out.printf("%-10s %-15s %-15s%n", "", "Merge Sort", (end - start) / 1_000_000.0 + " ms");


        // Time Quick Sort
        List<Book> copy2 = new ArrayList<>(books);
        start = System.nanoTime();
        sortSearchManager.quickSort(copy2, 0, copy2.size() - 1);
        end = System.nanoTime();
        // System.out.println("Quick Sort Time: " + (end - start) / 1_000_000.0 + " ms");
        System.out.printf("%-10s %-15s %-15s%n", "", "Quick Sort", (end - start) / 1_000_000.0 + " ms");

        System.out.println("-".repeat(40));
        System.out.println("Sorting methods performances have been compared successfully.");
        System.out.println("Note: The library books remains unsorted.");
        System.out.println("=".repeat(50) + "\n");
    }

    /**
     * Sorts books (list) by title and times the operation.
     * Utilizes the sort/search manager to
     * @param algo Sort algorithm being timed (either "merge" or "quick").
     */
    public void addBookByInput(Scanner scanner) {
        //
        try {
            int id = nextID++;

            // Prompts the user to enter the books details.
            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter Author: ");
            String author = scanner.nextLine();
            System.out.print("Enter Year Published: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Category: ");
            String category = scanner.nextLine();
            System.out.print("Type (1 for EBook, 2 for PrintedBook): ");
            int type = Integer.parseInt(scanner.nextLine());

            // Checks/Defines the type of book details being entered (either "EBook" or "PrintedBook").
            if (type == 1) {
                System.out.print("Enter Format (e.g., PDF): ");
                String format = scanner.nextLine();
                System.out.print("Enter File Size (MB): ");
                double fileSize = Double.parseDouble(scanner.nextLine());
                addBook(new EBook(id, title, author, year, category, format, fileSize));

            } else if (type == 2) {
                System.out.print("Enter Pages: ");
                int pages = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Publisher: ");
                String publisher = scanner.nextLine();
                addBook(new PrintedBook(id, title, author, year, category, pages, publisher));

            } else {
                System.out.println("-".repeat(40));
                System.out.println("Invalid type.");
                System.out.println("=".repeat(50) + "\n");
                // Revert ID if invalid
                nextID--;
            }
            // Catches invalid user inputs regarding the books details.
        } catch (NumberFormatException e) {
            System.out.println("-".repeat(40));
            System.out.println("Invalid number input.");
            System.out.println("=".repeat(50) + "\n");
            // Revert ID if invalid
            nextID--;
        } catch (IllegalArgumentException e) {
            System.out.println("-".repeat(40));
            System.out.println(e.getMessage());
            System.out.println("=".repeat(50) + "\n");
            // Revert ID if invalid
            nextID--;
        }
    }

    /**
     * Sorts books (list) by title and times the operation.
     * Utilizes the sort/search manager to
     * @param algo Sort algorithm being timed (either "merge" or "quick").
     */
    public void removeBookByInput(Scanner scanner) {
        // Sorts list ensure it's easier to search.
        sortSearchManager.sortListByTitle(books, "quick");
        // Prompts the user to enter the books title being search for.
        System.out.print("Enter Title to Remove Book: ");
        String title = scanner.nextLine();

        Book found = sortSearchManager.binarySearch(books, title);
        if (found != null) {
            removeBook(found);
        } else {
            System.out.println("-".repeat(40));
            System.out.println("You Entered: " + title);
            System.out.println("Book not found in library!");
            System.out.println("=".repeat(50) + "\n");
        }
    }

    /**
     * Searches for a book (in the library/list) by title from input.
     * Utilizes the sort/search manager to
     * @param scanner Input scanner to enter the book.
     */
    public void searchByInput(Scanner scanner) {
        // Sorts list ensure it's easier to search.
        sortSearchManager.sortListByTitle(books, "merge");
        // Prompts the user to enter the books title being search for.
        System.out.print("Enter Title to Search: ");
        String title = scanner.nextLine();

        Book found = sortSearchManager.binarySearch(books, title);
        System.out.println("-".repeat(40));
        // Checks if the book/title was found in the library/list.
        if (found != null) {
            System.out.println("Found: ");
            found.displayDetailsBlock();
            System.out.println("-".repeat(40));
            System.out.println("Book was found successfully");
        } else {
            System.out.println("You Entered: " + title);
            System.out.println("Book was not found.");
        }
        System.out.println("=".repeat(50) + "\n");
    }

    /**
     * Edits a book's details from input.
     * @param scanner Input scanner.
     */
    public void editBookByInput(Scanner scanner) {
        // Sorts list ensure it's easier to search.
        sortSearchManager.sortListByTitle(books, "merge");
        // Prompts the user to enter the books title being search for.
        System.out.print("Enter Title to Edit Book: ");
        String title = scanner.nextLine();

        Book book = sortSearchManager.binarySearch(books, title);
        if (book == null) {
            System.out.println("You entered: " + title);
            System.out.println("Book was not found.");
            System.out.println("=".repeat(50) + "\n");
            return;
        }

        System.out.println("-".repeat(40) + "\n");
        System.out.println("Current Details: ");
        book.displayDetails();

        System.out.println("Edit book's? (Common: 1.Title, 2.Author, 3.Year, 4.Category)");
        if (book instanceof EBook) {
            System.out.println("EBook-specific: 5.Format, 6.File Size");
            System.out.println("-".repeat(40));
        } else if (book instanceof PrintedBook) {
            System.out.println("PrintedBook-specific: 5.Pages, 6.Publisher");
            System.out.println("-".repeat(40));
        }
        System.out.print("Choose: ");
        int choice = Integer.parseInt(scanner.nextLine());

        try {
            switch (choice) {
                case 1:
                    System.out.println("-".repeat(40));
                    System.out.print("New Title: ");
                    book.setTitle(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("-".repeat(40));
                    System.out.print("New Author: ");
                    book.setAuthor(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("-".repeat(40));
                    System.out.print("New Year: ");
                    book.setYearPublished(Integer.parseInt(scanner.nextLine()));
                    break;
                case 4:
                    System.out.println("-".repeat(40));
                    System.out.print("New Category: ");
                    book.setCategory(scanner.nextLine());
                    break;
                case 5:
                    System.out.println("-".repeat(40));
                    if (book instanceof EBook) {
                        System.out.print("New Format: ");
                        ((EBook) book).setFormat(scanner.nextLine());
                    } else if (book instanceof PrintedBook) {
                        System.out.print("New Pages: ");
                        ((PrintedBook) book).setPages(Integer.parseInt(scanner.nextLine()));
                    } else {
                        System.out.println("Invalid for this type.");
                    }
                    break;
                case 6:
                    System.out.println("-".repeat(40));
                    if (book instanceof EBook) {
                        System.out.print("New File Size (MB): ");
                        ((EBook) book).setFileSize(Double.parseDouble(scanner.nextLine()));
                    } else if (book instanceof PrintedBook) {
                        System.out.print("New Publisher: ");
                        ((PrintedBook) book).setPublisher(scanner.nextLine());
                    } else {
                        System.out.println("Invalid for this type.");
                    }
                    break;
                default:
                    System.out.println("-".repeat(40));
                    System.out.println("Invalid choice.");
                    System.out.println("=".repeat(50) + "\n");
                    return;
            }
            System.out.println("Updated book:");
            book.displayDetails();
            System.out.println("-".repeat(40));
            System.out.println("Book was updated successfully.");
            System.out.println("=".repeat(50) + "\n");
        } catch (NumberFormatException e) {
            System.out.println("-".repeat(40));
            System.out.println("Invalid number input.");
            System.out.println("=".repeat(50) + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println("-".repeat(40));
            System.out.println(e.getMessage());
            System.out.println("=".repeat(50) + "\n");
        }
    }

    /**
     * Calculates a late fee for a book from input (only "PrintedBooks").
     * Utilizes the sort/search manager to
     * @param scanner Input scanner to enter the book.
     */
    public void calculateLateFeeByInput(Scanner scanner) {
        // Sorts list ensure it's easier to search.
        sortSearchManager.sortListByTitle(books, "merge");
        // Prompts the user to enter the books title being search for.
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();

        Book found = sortSearchManager.binarySearch(books, title);
        // Checks if the book/title was found in the library/list.
        if (found != null) {
            // Prompts the user to enter the number of days that the book is late.
            System.out.print("Enter days late: ");
            int lateDays = Integer.parseInt(scanner.nextLine());
            // Calculates a late fee for the books return based on the number of late days.
            System.out.println("Late fee for: " + title + " --> R/ZAR " + found.calculateLateFee(lateDays));
            System.out.println("-".repeat(40));
            System.out.println("Late fee was calculated successfully");
        } else {
            System.out.println("-".repeat(40));
            System.out.println("You Entered: " + title);
            System.out.println("Book was not found.");
        }
        System.out.println("=".repeat(50) + "\n");
    }

    /**
     * Groups library books by category.
     * @return Map of category to list of books.
     */
    public Map<String, List<Book>> groupBooksByCategory() {
        Map<String, List<Book>> groups = new HashMap<>();
        for (Book book : books) {
            String cat = book.getCategory().toLowerCase(); // Case-insensitive
            groups.computeIfAbsent(cat, k -> new ArrayList<>()).add(book);
        }
        return groups;
    }

    /**
     * Displays the groups of the different library books by category.
     */
    public void displayBooksByCategory() {
        Map<String, List<Book>> groups = groupBooksByCategory();
        if (groups.isEmpty()) {
            System.out.println("No books in the library to group by category.");
            System.out.println("=".repeat(50) + "\n");
            return;
        }
        for (Map.Entry<String, List<Book>> entry : groups.entrySet()) {
            System.out.println("Category: " + entry.getKey());
            for (Book book : entry.getValue()) {
                book.displayDetails();
            }
            System.out.println();
        }
        System.out.println("=".repeat(50) + "\n");
    }

    /**
     * Recommends a random book from the library and displays its details.
     */
    public void recommendRandomBook() {
        if (books.isEmpty()) {
            System.out.println("No books available for recommendation.");
            System.out.println("=".repeat(50) + "\n");
            return;
        }
        int index = random.nextInt(books.size());
        Book recommended = books.get(index);
        System.out.println("Recommended Book: ");
        recommended.displayDetailsBlock();
        System.out.println("-".repeat(40));
        System.out.println("Random book was recommended successfully");
        System.out.println("=".repeat(50) + "\n");
    }

    /**
     * Main method to demonstrate the system.
     * Includes undo/redo, sorting/searching, late fee examples.
     * @param args Command-line args (unused).
     */
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        UserInterface ui = new UserInterface(library);
        ui.menuStartUp();

        // Add sample books of different types for testing
        // EBooks
        library.addBook(new EBook(library.nextID++, "Digital Fortress", "Dan Brown", 1998,
                "Thriller", "EPUB", 2.5));
        library.addBook( new EBook(library.nextID++, "Clean Code", "Robert C. Martin",
                2008, "Programming", "PDF", 2.5));
        library.addBook( new EBook(library.nextID++, "Atomic Habits", "James Clear",
                2018, "Self-Help", "TXT", 3.25));
        library.addBook( new EBook(library.nextID++, "48 Laws of Power ", "Robert Greene",
                1998, "Self-Help", "PDF", 21.5));
        library.addBook( new EBook(library.nextID++, "How to Win Friends and Influence People", "Dale Carnegie",
                1936, "Self-Help", "EPUB", 32.8));
        library.addBook( new EBook(library.nextID++, "Dune", "Frank Herbert",
                1965, "Si-Fi", "TXT", 52.85));
        library.addBook( new EBook(library.nextID++, "Powerless", "Lauren Roberts",
                2023, "Novel", "PDF", 10.2));
        library.addBook( new EBook(library.nextID++, "Sunrise on the Reaping ", "Suzanne Collins",
                1965, "Si-Fi", "EPUB", 21.3));

        // PrintedBooks
        library.addBook( new PrintedBook(library.nextID++, "The Great Gatsby", "F. Scott Fitzgerald",
                1925, "Fiction", 210, "Charles Scribner's Sons"));
        library.addBook( new PrintedBook(library.nextID++, "Design Patterns", "Gang of Four",
                1994, "Programming", 102, "Addison-Wesley Professional"));
        library.addBook(new PrintedBook(library.nextID++, "The Alchemist", "Paulo Coelho", 1988,
                "Fiction", 208, "Harper Collins"));
        library.addBook(new PrintedBook(library.nextID++, "The Hobbit", "J. R. R. Tolkien", 1937,
                "Fantasy", 321, "Houghton Mifflin"));
        library.addBook(new PrintedBook(library.nextID++, "A Tale of Two Cities", "Charles Dickens", 1859,
                "Fiction", 454, "Chapman & Hall "));
        library.addBook(new PrintedBook(library.nextID++, "Alice's Adventures in Wonderland", "Lewis Carroll", 1865,
                "Fantasy", 352, "Macmillan Publishers"));
        library.addBook(new PrintedBook(library.nextID++, "1984", "George Orwell", 1949,
                "Fiction", 684, "Secker & Warburg"));
        library.addBook(new PrintedBook(library.nextID++, "Beloved", "Toni Morrison", 1988,
                "Novel", 276, "Alfred A. Knopf"));

        ui.runMenu();
    }

}
