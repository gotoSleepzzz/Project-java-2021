# Project-java-2021

Hệ thống quản lý covid

| MSSV     | Họ tên               |
| -------- | -------------------- |
| 19127447 | Nguyễn Phan Anh Khoa |
| 19127564 | Nguyễn Hữu Thông     |
| 19124576 | Phạm Đoàn Tiến       |
| 19127603 | Đỗ Tiến Trung        |

- Genkey:
  - Step 1 : `keytool -genkey -keyalg RSA -keysize 2048 -validity 360 -alias mykey -keystore myKeyStore.jks`
  - Step 2 : `keytool -export -alias mykey -keystore myKeyStore.jks -file mykey.cert`
  - Step 3 : `keytool -import -file mykey.cert -alias mykey -keystore myTrustStore.jts`
