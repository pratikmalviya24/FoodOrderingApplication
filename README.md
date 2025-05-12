# Food Ordering Application

A full-stack food ordering platform that allows users to browse restaurants, order food, and track deliveries. The application consists of a Spring Boot backend and React frontend.

## Features

### User Features
- User authentication and authorization
- Browse restaurants and their menus
- Search and filter food items
- Shopping cart functionality
- Order placement and tracking
- Multiple delivery addresses management
- Order history
- User profile management
- Dark/Light theme support

### Restaurant Features
- Restaurant profile management
- Menu and food item management
- Order management
- Ingredient management
- Category management

## Tech Stack

### Backend
- Java Spring Boot
- Spring Security with JWT Authentication
- Spring Data JPA
- MySQL Database
- RESTful API Architecture

### Frontend
- React.js
- Redux for state management
- Tailwind CSS
- React Router for navigation

## Project Structure

```
├── backend/                 # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/      # Java source files
│   │   │   └── resources/ # Application properties
│   │   └── test/          # Test files
│   └── pom.xml            # Maven dependencies
│
└── frontend/              # React frontend
    ├── public/           # Static files
    ├── src/
    │   ├── components/   # React components
    │   ├── routes/       # Route definitions
    │   ├── state/       # Redux state management
    │   └── theme/       # Theme configuration
    ├── package.json     # NPM dependencies
    └── tailwind.config.js
```

## Getting Started

### Backend Setup
1. Ensure you have JDK 11+ and Maven installed
2. Configure MySQL database in `backend/src/main/resources/application.properties`
3. Navigate to backend directory:
   ```bash
   cd backend
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
   The backend server will start on port 8080

### Frontend Setup
1. Ensure you have Node.js 16+ installed
2. Navigate to frontend directory:
   ```bash
   cd frontend
   ```
3. Install dependencies:
   ```bash
   npm install
   ```
4. Start the development server:
   ```bash
   npm start
   ```
   The frontend application will start on port 3000

## API Endpoints

### Auth Endpoints
- POST `/api/auth/register` - Register new user
- POST `/api/auth/login` - User login

### User Endpoints
- GET `/api/users/profile` - Get user profile
- PUT `/api/users/profile` - Update user profile

### Restaurant Endpoints
- GET `/api/restaurants` - List all restaurants
- GET `/api/restaurants/{id}` - Get restaurant details
- GET `/api/restaurants/{id}/menu` - Get restaurant menu

### Order Endpoints
- POST `/api/orders` - Create new order
- GET `/api/orders` - Get user orders
- GET `/api/orders/{id}` - Get order details

### Cart Endpoints
- GET `/api/cart` - Get user's cart
- POST `/api/cart/items` - Add item to cart
- PUT `/api/cart/items/{id}` - Update cart item
- DELETE `/api/cart/items/{id}` - Remove item from cart

## Contributing
Feel free to submit issues and enhancement requests.

## License
[MIT License](LICENSE)
