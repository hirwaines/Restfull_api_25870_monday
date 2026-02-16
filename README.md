# Assignment Implementation Summary

This document explains what was implemented for each question in the RESTful API assignment.

---

## Question 1: Library Book Management API (20 Points)

### What Was Required:
- Book class with id, title, author, isbn, publicationYear
- 5 REST endpoints for book management
- 3 sample books
- HTTP status codes (200, 201, 204, 404)

### What Was Implemented:

**`Book.java`**
- Created with all required attributes (id, title, author, isbn, publicationYear)
- Added getters and setters for each property

**`BookController.java`**
- ✅ GET `/api/books` - Returns list of all books
- ✅ GET `/api/books/{id}` - Returns specific book by ID with 404 if not found
- ✅ GET `/api/books/search?title={title}` - Searches books by title (case-insensitive)
- ✅ POST `/api/books` - Adds new book with 201 CREATED status
- ✅ DELETE `/api/books/{id}` - Deletes book with 204 NO_CONTENT status
- Initialized with 3 sample books: Clean Code, Effective Java, Design Patterns
- Used ResponseEntity for proper HTTP status codes

**Result:** All requirements met including bonus HTTP status codes

---

## Question 2: Student Registration API (20 Points)

### What Was Required:
- Student class with studentId, firstName, lastName, email, major, gpa
- 6 REST endpoints
- 5 sample students with different majors and GPAs
- Test filtering by Computer Science major and GPA >= 3.5

### What Was Implemented:

**`Student.java`**
- Created with all required attributes
- Includes getters and setters

**`StudentController.java`**
- ✅ GET `/api/students` - Returns all students
- ✅ GET `/api/students/{studentId}` - Returns student by ID
- ✅ GET `/api/students/major/{major}` - Filters by major (path variable)
- ✅ GET `/api/students/filter?gpa={minGpa}` - Filters students with GPA >= minimum
- ✅ POST `/api/students` - Registers new student with 201 status
- ✅ PUT `/api/students/{studentId}` - Updates student information
- Initialized with 5 students:
  - Computer Science major with GPA 3.8
  - Business major with GPA 3.5
  - Engineering major with GPA 3.9
  - Biology major with GPA 3.2
  - Mathematics major with GPA 3.6
- Can filter Computer Science students
- Can filter students with GPA >= 3.5 (returns 4 students)

**Result:** All requirements and testing scenarios met

---

## Question 3: Restaurant Menu API (20 Points)

### What Was Required:
- MenuItem class with id, name, description, price, category, available
- 8 REST endpoints
- 8+ menu items across all categories (Appetizer, Main Course, Dessert, Beverage)

### What Was Implemented:

**`MenuItem.java`**
- Created with all required attributes
- Boolean available flag for item status

**`MenuController.java`**
- ✅ GET `/api/menu` - Returns all menu items
- ✅ GET `/api/menu/{id}` - Returns specific menu item
- ✅ GET `/api/menu/category/{category}` - Filters by category
- ✅ GET `/api/menu/available?available=true` - Returns only available items
- ✅ GET `/api/menu/search?name={name}` - Searches by name
- ✅ POST `/api/menu` - Adds new menu item
- ✅ PUT `/api/menu/{id}/availability` - Toggles availability
- ✅ DELETE `/api/menu/{id}` - Removes menu item
- Created 8+ items covering all categories:
  - Appetizers: Spring Rolls, Bruschetta
  - Main Courses: Grilled Salmon, Beef Steak
  - Desserts: Chocolate Cake, Tiramisu
  - Beverages: Fresh Juice, Cappuccino

**Result:** All requirements met including challenge of 8+ items

---

## Question 4: E-Commerce Product API (25 Points)

### What Was Required:
- Product class with productId, name, description, price, category, stockQuantity, brand
- 11 REST endpoints including pagination, multiple filters, and PATCH
- 10+ products with different categories, brands, and prices
- Test all search and filter functionalities

### What Was Implemented:

**`Product.java`**
- Created with all required attributes
- Includes inventory tracking with stockQuantity

**`ProductController.java`**
- ✅ GET `/api/products` - Returns all products with optional pagination (?page=&limit=)
- ✅ GET `/api/products/{productId}` - Returns product details
- ✅ GET `/api/products/category/{category}` - Filters by category
- ✅ GET `/api/products/brand/{brand}` - Filters by brand
- ✅ GET `/api/products/search?keyword={keyword}` - Searches name and description
- ✅ GET `/api/products/price-range?min={min}&max={max}` - Filters by price range
- ✅ GET `/api/products/in-stock` - Returns products with stock > 0
- ✅ POST `/api/products` - Adds new product
- ✅ PUT `/api/products/{productId}` - Updates product details
- ✅ PATCH `/api/products/{productId}/stock?quantity={quantity}` - Updates stock only
- ✅ DELETE `/api/products/{productId}` - Deletes product
- Created 10+ products with variety:
  - Multiple categories: Electronics, Clothing, Books, Home & Kitchen
  - Multiple brands: Samsung, Apple, Nike, Adidas, etc.
  - Price range: $9.99 to $1299.99
  - Various stock levels (0 to 200)
- All filters tested and working

**Result:** All requirements met with comprehensive filtering

---

## Question 5: Task Management API (15 Points)

### What Was Required:
- Task class with taskId, title, description, completed, priority, dueDate
- Priority levels: LOW, MEDIUM, HIGH
- 8 REST endpoints including PATCH for completion

### What Was Implemented:

**`Task.java`**
- Created with all required attributes
- Priority as String with LOW/MEDIUM/HIGH values
- Date format: YYYY-MM-DD

**`TaskController.java`**
- ✅ GET `/api/tasks` - Returns all tasks
- ✅ GET `/api/tasks/{taskId}` - Returns task by ID
- ✅ GET `/api/tasks/status?completed={true/false}` - Filters by completion status
- ✅ GET `/api/tasks/priority/{priority}` - Filters by priority level
- ✅ POST `/api/tasks` - Creates new task
- ✅ PUT `/api/tasks/{taskId}` - Updates task
- ✅ PATCH `/api/tasks/{taskId}/complete` - Marks task as completed
- ✅ DELETE `/api/tasks/{taskId}` - Deletes task
- Sample tasks with different priorities and completion statuses

**Result:** All requirements met

---

## Question 6: User Profile API - Bonus (20 Points)

### What Was Required:
- UserProfile class with userId, username, email, fullName, age, country, bio, active
- Multiple endpoints including CRUD, search, and activate/deactivate
- Custom response wrapper with success, message, and data fields
- Return wrapped responses

### What Was Implemented:

**`UserProfile.java`**
- Created with all required attributes
- Boolean active flag for account status

**`UserProfileController.java`**
- ✅ Created inner Response<T> class with:
  - boolean success
  - String message
  - T data (generic type)
  - Constructor and getters
- ✅ GET `/api/users` - Returns all users
- ✅ GET `/api/users/{userId}` - Returns user with wrapped response
- ✅ GET `/api/users/username/{username}` - Searches by username
- ✅ GET `/api/users/country/{country}` - Filters by country
- ✅ GET `/api/users/age-range?min={min}&max={max}` - Filters by age range
- ✅ POST `/api/users` - Creates user with wrapped response and success message
- ✅ PUT `/api/users/{userId}` - Updates user profile
- ✅ PATCH `/api/users/{userId}/activate` - Activates user account
- ✅ PATCH `/api/users/{userId}/deactivate` - Deactivates user account
- ✅ DELETE `/api/users/{userId}` - Deletes user profile
- All responses wrapped in ApiResponse format:
  ```json
  {
    "success": true,
    "message": "User profile created successfully",
    "data": { user object }
  }
  ```

**Result:** All bonus requirements met with response wrapper

---

## Common Implementation Details

### Technologies Used:
- **Java 21** - Programming language
- **Spring Boot 4.0.2** - Framework
- **Spring Web MVC** - REST API support
- **Maven** - Build tool

### Architecture:
- **Model Layer** - POJO classes with getters/setters
- **Controller Layer** - REST endpoints with annotations
- **In-Memory Storage** - ArrayList for data persistence

### HTTP Methods Used:
- **GET** - Retrieve resources
- **POST** - Create new resources (201 CREATED)
- **PUT** - Full update of resources
- **PATCH** - Partial update of resources
- **DELETE** - Remove resources (204 NO_CONTENT)

### Status Codes Implemented:
- **200 OK** - Successful GET/PUT/PATCH
- **201 CREATED** - Successful POST
- **204 NO_CONTENT** - Successful DELETE
- **404 NOT_FOUND** - Resource not found

### Key Annotations Used:
- `@RestController` - Marks REST controller
- `@RequestMapping` - Base URL mapping
- `@GetMapping` - GET endpoints
- `@PostMapping` - POST endpoints
- `@PutMapping` - PUT endpoints
- `@PatchMapping` - PATCH endpoints
- `@DeleteMapping` - DELETE endpoints
- `@PathVariable` - URL path parameters
- `@RequestParam` - Query parameters
- `@RequestBody` - Request body binding
- `@ResponseStatus` - HTTP status codes

---

## Testing Capabilities

Each API can be tested with:

### Using cURL:
```bash
# GET all resources
curl http://localhost:8080/api/books

# GET by ID
curl http://localhost:8080/api/books/1

# POST new resource
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"id":4,"title":"New Book","author":"Author","isbn":"123","publicationYear":2024}'

# DELETE resource
curl -X DELETE http://localhost:8080/api/books/1
```

### Using Postman:
- Import base URL: `http://localhost:8080`
- Test all endpoints with different HTTP methods
- Verify response status codes and JSON structure

---

## Running the Projects

### Start any project:
```bash
cd question[X]-[project-name]-api
./mvnw spring-boot:run
```

### Windows:
```cmd
cd question[X]-[project-name]-api
mvnw.cmd spring-boot:run
```

### Access API:
- Default port: 8080
- Example: `http://localhost:8080/api/books`

---


