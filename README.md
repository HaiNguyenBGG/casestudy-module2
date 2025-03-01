# 🛒 HỆ THỐNG QUẢN LÝ CỬA HÀNG
📌 **Mô tả**  
Hệ thống quản lý cửa hàng đơn giản bằng Java, sử dụng **DAO Pattern, File IO (CSV, Binary), Multithreading, Exception Handling, Regex Validation**, tuân thủ **MVC Architecture**.

---

## 🚀 **1. Chức Năng Chính**
- **📦 Quản lý Sản phẩm** (Thêm, Xóa, Sửa, Xem danh sách)
- **👥 Quản lý Khách hàng** (Thêm, Xóa, Sửa, Xem danh sách)
- **🛒 Quản lý Đơn hàng** (Thêm, Xóa, Xem danh sách)
- **💾 Lưu dữ liệu vào file Nhị phân (`.dat`) & CSV (`.csv`)**
- **📝 Ghi log thao tác người dùng vào file (`logs/log.txt`)**
- **✅ Kiểm tra dữ liệu nhập vào bằng Regex (Email, SĐT, Ngày)**
- **💡 Tương tác bằng giao diện Terminal (CLI Menu)**

---

## 🛠 **2. Công Nghệ Sử Dụng**
- **Ngôn ngữ:** Java
- **Lưu trữ dữ liệu:** File CSV & File Nhị phân
- **Kiến trúc:** MVC (Model-View-Controller)
- **Xử lý ngoại lệ:** Exception Handling
- **Đa luồng:** ExecutorService để ghi log
- **Regex Validation:** Kiểm tra Email, SĐT, Định dạng ngày

---

## 📂 **3. Cấu Trúc Thư Mục**
```plaintext
store-management/
│── src/
│   ├── com/store/
│   │   ├── manager/      # Quản lý logic nghiệp vụ
│   │   ├── model/        # Định nghĩa đối tượng (Product, Customer, Order...)
│   │   ├── storage/      # Xử lý dữ liệu (DAO, File IO)
│   │   │   ├── dao/      # Interface DAO (ProductDAO, OrderDAO...)
│   │   │   ├── impl/     # Cài đặt DAO (ProductDAOImpl, OrderDAOImpl...)
│   │   │   ├── fileHandler/ # Đọc/Ghi file CSV & Nhị phân
│   │   │   ├── utils/    # Các tiện ích (Log, AutoSave)
│   │   ├── view/         # Giao diện CLI (MainView, ProductView...)
│── logs/                 # Lưu file log
│── data/                 # Lưu file CSV & Nhị phân
│── README.md             # Tài liệu hướng dẫn

