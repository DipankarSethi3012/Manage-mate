# Manage-mate

**Manage-mate** is a Project Management System designed specifically for college students. It helps manage projects efficiently by allowing users to create issues, add team members, and streamline project workflows.

## 🚀 Features
- **Project Management:** Create, update, and track projects.
- **Issue Tracking:** Report, assign, and resolve project-related issues.
- **Team Collaboration:** Add team members and manage roles.
- **Secure Authentication:** Implemented with JWT and Spring Security.
- **Payment Integration:** Integrated with Razorpay for managing project-related payments.
- **Email Notifications:** Send project updates and notifications using JavaMailer.

## 🛠️ Tech Stack
- **Backend:** Spring Boot
- **Security:** Spring Security, JWT (JSON Web Tokens)
- **Database:** Spring Data JPA
- **Email Service:** JavaMailer
- **Payment Gateway:** Razorpay
- **Programming Language:** Java

## 📦 Installation & Setup
1. **Clone the repository:**  
   ```bash
   git clone https://github.com/DipankarSethi3012/Manage-mate.git
   cd Manage-mate
   ```

2. **Configure the application:**  
   - Update the `application.properties` file with your database credentials, JWT secret key, Razorpay API keys, and email configurations.

3. **Build the project:**  
   ```bash
   ./mvnw clean install
   ```

4. **Run the application:**  
   ```bash
   ./mvnw spring-boot:run
   ```

## 📋 API Endpoints
- **Authentication:** `/api/auth/login`, `/api/auth/register`
- **Projects:** `/api/projects`
- **Issues:** `/api/issues`
- **Payments:** `/api/payments`
- **Email Notifications:** `/api/notifications`

## ✅ Contributing
1. Fork the repository
2. Create a new branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Open a Pull Request

## 📧 Contact
For queries, reach out to the project maintainer: [Dipankar Sethi](https://github.com/DipankarSethi3012)

---

**Manage-mate** — Simplifying project management for college students 🚀

