// Định nghĩa cấu hình thanh toán payOSConfig
const payOSConfig = {
  RETURN_URL: "http://localhost:9999/FitnessMedia/home", // bắt buộc
  ELEMENT_ID: "payment-container", // bắt buộc
  CHECKOUT_URL: "http://localhost:9999/FitnessMedia/BookingHistory", // bắt buộc
  onSuccess: function(event) {
    // Hành động sau khi thanh toán thành công
  },
  onCancel: function(event) {
    // Hành động sau khi người dùng hủy thanh toán
  },
  onExit: function(event) {
    // Hành động sau khi người dùng đóng cửa sổ popup thanh toán
  }
};

// Sử dụng hàm usePayOS từ PayOSCheckout để tạo các hàm open và exit
const { open, exit } = PayOSCheckout.usePayOS(payOSConfig);
