import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Book> listBook = new ArrayList<>();
        int chon = 0;

        do {
            // Menu chương trình
            System.out.println("\n========= MENU QUẢN LÝ SÁCH =========");
            System.out.println("1. Thêm 1 cuốn sách");
            System.out.println("2. Xóa 1 cuốn sách (theo ID)");
            System.out.println("3. Thay đổi thông tin sách (theo ID)");
            System.out.println("4. Xuất thông tin tất cả sách");
            System.out.println("5. Tìm sách có tựa đề chứa 'Lập trình'");
            System.out.println("6. Tìm tối đa K cuốn sách có giá <= P");
            System.out.println("7. Tìm sách theo danh sách tác giả");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            
            try {
                chon = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                chon = -1; // Xử lý nếu nhập chữ thay vì số
            }

            switch (chon) {
                case 1 -> {
                    System.out.println("--- Thêm sách mới ---");
                    Book book = new Book();
                    book.input();
                    listBook.add(book);
                    System.out.println("Đã thêm thành công!");
                }

                case 2 -> {
                    System.out.print("Nhập ID sách cần xóa: ");
                    int idDel = Integer.parseInt(scanner.nextLine());
                    boolean removed = listBook.removeIf(b -> b.getId() == idDel);
                    if (removed) System.out.println("Đã xóa sách có ID " + idDel);
                    else System.out.println("Không tìm thấy ID này.");
                }

                case 3 -> {
                    System.out.print("Nhập ID sách cần sửa: ");
                    int idEdit = Integer.parseInt(scanner.nextLine());
                    Book bookToEdit = listBook.stream()
                            .filter(b -> b.getId() == idEdit)
                            .findFirst()
                            .orElse(null);

                    if (bookToEdit != null) {
                        System.out.println("Nhập thông tin mới (ID giữ nguyên):");
                        int oldId = bookToEdit.getId(); 
                        bookToEdit.input();
                        bookToEdit.setId(oldId);
                        System.out.println("Cập nhật thành công!");
                    } else {
                        System.out.println("Không tìm thấy sách!");
                    }
                }

                case 4 -> {
                    System.out.println("--- Danh sách sách ---");
                    if (listBook.isEmpty()) System.out.println("(Trống)");
                    else listBook.forEach(Book::output); // Method Reference
                }

                case 5 -> {
                    
                    System.out.println("--- Sách Lập Trình ---");
                    listBook.stream()
                            .filter(b -> b.getTitle().toLowerCase().contains("lập trình"))
                            .forEach(Book::output);
                }

                case 6 -> {
                    
                    System.out.print("Nhập số lượng K cần lấy: ");
                    int k = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nhập mức giá trần P: ");
                    long p = Long.parseLong(scanner.nextLine());

                    System.out.println("--- Kết quả tìm kiếm ---");
                    listBook.stream()
                            .filter(b -> b.getPrice() <= p) // Lọc giá
                            .limit(k)                       // Lấy tối đa K cuốn
                            .forEach(Book::output);
                }

                case 7 -> {
                    System.out.print("Nhập các tác giả (cách nhau bởi dấu phẩy): ");
                    String inputAuthors = scanner.nextLine();
                    List<String> targetAuthors = Arrays.stream(inputAuthors.split(","))
                            .map(String::trim)
                            .map(String::toLowerCase)
                            .collect(Collectors.toList());

                    System.out.println("--- Sách của các tác giả: " + inputAuthors + " ---");
                    listBook.stream()
                            .filter(b -> targetAuthors.contains(b.getAuthor().toLowerCase()))
                            .forEach(Book::output);
                }

                case 0 -> System.out.println("Kết thúc chương trình.");
                
                default -> System.out.println("Vui lòng chọn từ 0 đến 7.");
            }

        } while (chon != 0);
    }
}