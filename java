
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SPA - Sistema de Usuários</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; font-family: Arial, sans-serif; }
        body { background: #f5f5f5; }
        .container { max-width: 800px; margin: 0 auto; padding: 20px; }
        nav { background: #2c3e50; padding: 1rem; margin-bottom: 2rem; }
        nav a { color: white; text-decoration: none; margin-right: 20px; padding: 5px 10px; }
        nav a.active { background: #3498db; border-radius: 3px; }
        .page { display: none; }
        .page.active { display: block; animation: fadeIn 0.3s; }
        @keyframes fadeIn { from { opacity: 0; } to { opacity: 1; } }
        .card { background: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); margin-bottom: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; }
        .error { color: #e74c3c; font-size: 0.8rem; margin-top: 5px; display: none; }
        .error.show { display: block; }
        button { background: #3498db; color: white; border: none; padding: 10px 15px; border-radius: 4px; cursor: pointer; }
        .user-card { border: 1px solid #ddd; padding: 15px; margin-bottom: 10px; border-radius: 4px; }
    </style>
</head>
<body>
    <nav>
        <div class="container">
            <a href="#home" class="nav-link active">Home</a>
            <a href="#users" class="nav-link">Usuários</a>
            <a href="#add" class="nav-link">Adicionar</a>
        </div>
    </nav>

    <div class="container">
        <!-- Páginas -->
        <div id="home-page" class="page active">
            <div class="card">
                <h2>Bem-vindo ao Sistema SPA</h2>
                <p>Esta aplicação demonstra Single Page Application com validação de formulários.</p>
            </div>
        </div>

        <div id="users-page" class="page">
            <div class="card">
                <h2>Lista de Usuários</h2>
                <div id="users-list"></div>
            </div>
        </div>

        <div id="add-page" class="page">
            <div class="card">
                <h2>Adicionar Usuário</h2>
                <form id="user-form">
                    <div class="form-group">
                        <label for="name">Nome:</label>
                        <input type="text" id="name" name="name">
                        <div class="error" id="name-error">Nome deve ter pelo menos 3 caracteres</div>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">E-mail:</label>
                        <input type="email" id="email" name="email">
                        <div class="error" id="email-error">E-mail inválido</div>
                    </div>
                    
                    <div class="form-group">
                        <label for="age">Idade:</label>
                        <input type="number" id="age" name="age">
                        <div class="error" id="age-error">Idade deve ser entre 18 e 100</div>
                    </div>
                    
                    <button type="submit">Cadastrar</button>
                </form>
            </div>
        </div>
    </div>

    <script>
        // Sistema de Roteamento SPA
        class Router {
            constructor() {
                this.routes = {
                    'home': 'home-page',
                    'users': 'users-page', 
                    'add': 'add-page'
                };
                this.init();
            }

            init() {
                // Navegação por links
                document.querySelectorAll('.nav-link').forEach(link => {
                    link.addEventListener('click', (e) => {
                        e.preventDefault();
                        const route = e.target.getAttribute('href').substring(1);
                        this.navigate(route);
                    });
                });

                // Navegação inicial
                window.addEventListener('load', () => {
                    const route = window.location.hash.substring(1) || 'home';
                    this.navigate(route);
                });
            }

            navigate(route) {
                // Atualizar navegação
                document.querySelectorAll('.nav-link').forEach(link => link.classList.remove('active'));
                document.querySelector(`[href="#${route}"]`).classList.add('active');

                // Mostrar página correta
                document.querySelectorAll('.page').forEach(page => page.classList.remove('active'));
                document.getElementById(this.routes[route]).classList.add('active');

                // Ações específicas
                if (route === 'users') UserManager.renderUsers();
                if (route === 'add') document.getElementById('user-form').reset();
            }
        }

        // Validação de Formulários
        class FormValidator {
            static init() {
                const form = document.getElementById('user-form');
                
                // Validação em tempo real
                ['name', 'email', 'age'].forEach(field => {
                    document.getElementById(field).addEventListener('blur', (e) => {
                        this.validateField(e.target);
                    });
                });

                // Validação no submit
                form.addEventListener('submit', (e) => {
                    e.preventDefault();
                    if (this.validateForm()) {
                        UserManager.addUser(this.getFormData());
                        form.reset();
                        router.navigate('users');
                    }
                });
            }

            static validateField(field) {
                const value = field.value.trim();
                let isValid = true;
                let error = '';

                switch(field.id) {
                    case 'name':
                        if (value.length < 3) {
                            isValid = false;
                            error = 'Nome deve ter pelo menos 3 caracteres';
                        }
                        break;
                    case 'email':
                        if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value)) {
                            isValid = false;
                            error = 'E-mail inválido';
                        }
                        break;
                    case 'age':
                        const age = parseInt(value);
                        if (age < 18 || age > 100) {
                            isValid = false;
                            error = 'Idade deve ser entre 18 e 100';
                        }
                        break;
                }

                this.showError(field.id, isValid, error);
                return isValid;
            }

            static validateForm() {
                let isValid = true;
                ['name', 'email', 'age'].forEach(fieldId => {
                    const field = document.getElementById(fieldId);
                    if (!this.validateField(field)) isValid = false;
                });
                return isValid;
            }

            static showError(fieldId, isValid, message) {
                const errorElement = document.getElementById(`${fieldId}-error`);
                const field = document.getElementById(fieldId);
                
                if (isValid) {
                    errorElement.classList.remove('show');
                    field.style.borderColor = '#ddd';
                } else {
                    errorElement.textContent = message;
                    errorElement.classList.add('show');
                    field.style.borderColor = '#e74c3c';
                }
            }

            static getFormData() {
                return {
                    name: document.getElementById('name').value,
                    email: document.getElementById('email').value,
                    age: document.getElementById('age').value
                };
            }
        }

        // Gerenciamento de Usuários
        class UserManager {
            static getUsers() {
                return JSON.parse(localStorage.getItem('users') || '[]');
            }

            static saveUsers(users) {
                localStorage.setItem('users', JSON.stringify(users));
            }

            static addUser(userData) {
                const users = this.getUsers();
                users.push({
                    id: Date.now(),
                    ...userData
                });
                this.saveUsers(users);
            }

            static deleteUser(userId) {
                const users = this.getUsers().filter(user => user.id !== userId);
                this.saveUsers(users);
                this.renderUsers();
            }

            static renderUsers() {
                const container = document.getElementById('users-list');
                const users = this.getUsers();

                if (users.length === 0) {
                    container.innerHTML = '<p>Nenhum usuário cadastrado</p>';
                    return;
                }

                container.innerHTML = users.map(user => `
                    <div class="user-card">
                        <h3>${user.name}</h3>
                        <p>E-mail: ${user.email}</p>
                        <p>Idade: ${user.age} anos</p>
                        <button onclick="UserManager.deleteUser(${user.id})">Excluir</button>
                    </div>
                `).join('');
            }
        }

        // Inicialização
        const router = new Router();
        FormValidator.init();
        UserManager.renderUsers();
    </script>
</body>
</htm
