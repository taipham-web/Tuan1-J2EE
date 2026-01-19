import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private long price;

    // Constructor rỗng
    public Book() {
    }

    // Constructor đầy đủ tham số
    public Book(int id, String title, String author, long price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getter và Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public long getPrice() { return price; }
    public void setPrice(long price) { this.price = price; }

    public void input() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mã sách: ");
        this.id = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Nhập tên sách: ");
        this.title = scanner.nextLine();
        
        System.out.print("Nhập tác giả: ");
        this.author = scanner.nextLine();
        
        System.out.print("Nhập đơn giá: ");
        this.price = Long.parseLong(scanner.nextLine());
    }

    public void output() {
        String msg = """
                     BOOK: id= %d, title= %s, author= %s, price= %d
                     """.formatted(id, title, author, price);
        System.out.print(msg);
    }
}