<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Design</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        /* ===== VARIÁVEIS CSS ===== */
        :root {
            /* Cores */
            --primary-50: #e3f2fd; --primary-500: #2196f3; --primary-900: #0d47a1;
            --secondary-50: #fce4ec; --secondary-500: #e91e63; --secondary-900: #880e4f;
            --neutral-50: #fafafa; --neutral-500: #9e9e9e; --neutral-900: #212121;
            
            /* Tipografia */
            --font-family: 'Segoe UI', sans-serif;
            --font-size-sm: 0.875rem; --font-size-base: 1rem; --font-size-lg: 1.125rem;
            --font-size-xl: 1.25rem; --font-size-2xl: 1.5rem; --font-size-3xl: 1.875rem;
            
            /* Espaçamento */
            --space-1: 0.5rem; --space-2: 1rem; --space-3: 1.5rem; 
            --space-4: 2rem; --space-5: 3rem; --space-6: 4rem;
            
            /* Bordas e Sombras */
            --border-radius: 0.5rem; --border-radius-lg: 1rem;
            --shadow: 0 3px 6px rgba(0,0,0,0.15); --shadow-lg: 0 10px 20px rgba(0,0,0,0.15);
            
            /* Transições */
            --transition: 0.3s ease;
        }

        /* ===== RESET E BASE ===== */
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: var(--font-family); background-color: var(--neutral-50); color: var(--neutral-900); }

        /* ===== LAYOUT GRID ===== */
        .container { max-width: 1200px; margin: 0 auto; padding: 0 var(--space-2); }
        .grid { display: grid; grid-template-columns: repeat(12, 1fr); gap: var(--space-3); }
        .col-4 { grid-column: span 4; } .col-6 { grid-column: span 6; } .col-12 { grid-column: span 12; }

        /* ===== NAVEGAÇÃO ===== */
        .navbar { background: white; box-shadow: var(--shadow); position: sticky; top: 0; z-index: 1000; }
        .nav-container { display: flex; justify-content: space-between; align-items: center; padding: var(--space-2) 0; }
        .logo { font-size: var(--font-size-2xl); font-weight: 700; color: var(--primary-500); text-decoration: none; }
        .nav-menu { display: flex; list-style: none; gap: var(--space-4); }
        .nav-link { text-decoration: none; color: var(--neutral-700); font-weight: 500; transition: color var(--transition); }
        .nav-link:hover { color: var(--primary-500); }
        .dropdown-menu { position: absolute; background: white; box-shadow: var(--shadow-lg); border-radius: var(--border-radius); opacity: 0; visibility: hidden; transition: all var(--transition); }
        .nav-item:hover .dropdown-menu { opacity: 1; visibility: visible; }
        .hamburger { display: none; flex-direction: column; cursor: pointer; gap: 4px; }
        .hamburger span { width: 25px; height: 3px; background: var(--neutral-700); transition: all var(--transition); }

        /* ===== BOTÕES ===== */
        .btn { display: inline-flex; align-items: center; gap: var(--space-1); padding: var(--space-1) var(--space-3); border: none; border-radius: var(--border-radius); font-weight: 500; text-decoration: none; cursor: pointer; transition: all var(--transition); }
        .btn-primary { background: var(--primary-500); color: white; }
        .btn-primary:hover { background: var(--primary-600); transform: translateY(-2px); box-shadow: var(--shadow); }
        .btn-secondary { background: var(--secondary-500); color: white; }
        .btn-outline { background: transparent; border: 1px solid var(--primary-500); color: var(--primary-500); }
        .btn-outline:hover { background: var(--primary-500); color: white; }

        /* ===== CARDS ===== */
        .card { background: white; border-radius: var(--border-radius-lg); box-shadow: var(--shadow); overflow: hidden; transition: transform var(--transition); }
        .card:hover { transform: translateY(-5px); box-shadow: var(--shadow-lg); }
        .card-body { padding: var(--space-3); }
        .card-title { font-size: var(--font-size-xl); margin-bottom: var(--space-2); }

        /* ===== BADGES ===== */
        .badge { display: inline-block; padding: 0.25rem var(--space-1); font-size: var(--font-size-sm); font-weight: 600; border-radius: 2rem; }
        .badge-primary { background: var(--primary-100); color: var(--primary-800); }
        .badge-success { background: #e8f5e9; color: #2e7d32; }

        /* ===== FORMULÁRIOS ===== */
        .form-group { margin-bottom: var(--space-3); }
        .form-label { display: block; margin-bottom: var(--space-1); font-weight: 500; }
        .form-control { width: 100%; padding: var(--space-2); border: 1px solid var(--neutral-400); border-radius: var(--border-radius); transition: border-color var(--transition); }
        .form-control:focus { outline: none; border-color: var(--primary-500); box-shadow: 0 0 0 3px rgba(33, 150, 243, 0.1); }

        /* ===== ALERTAS ===== */
        .alert { padding: var(--space-2) var(--space-3); border-radius: var(--border-radius); margin-bottom: var(--space-3); border-left: 4px solid; }
        .alert-info { background: var(--primary-50); color: var(--primary-800); border-color: var(--primary-500); }
        .alert-success { background: #e8f5e9; color: #2e7d32; border-color: #4caf50; }

        /* ===== MODAL ===== */
        .modal-overlay { position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0,0,0,0.5); display: flex; align-items: center; justify-content: center; z-index: 1200; opacity: 0; visibility: hidden; transition: all var(--transition); }
        .modal-overlay.active { opacity: 1; visibility: visible; }
        .modal { background: white; border-radius: var(--border-radius-lg); box-shadow: var(--shadow-lg); width: 90%; max-width: 500px; transform: scale(0.9); transition: transform var(--transition); }
        .modal-overlay.active .modal { transform: scale(1); }
        .modal-header { padding: var(--space-3); border-bottom: 1px solid var(--neutral-200); display: flex; justify-content: space-between; }

        /* ===== SEÇÕES ===== */
        .section { padding: var(--space-6) 0; }
        .section-header { text-align: center; margin-bottom: var(--space-5); }
        .section-title { font-size: var(--font-size-3xl); margin-bottom: var(--space-2); }
        .hero { background: linear-gradient(135deg, var(--primary-500) 0%, var(--primary-700) 100%); color: white; padding: var(--space-6) 0; }
        .hero-content { display: flex; flex-direction: column; align-items: center; text-align: center; gap: var(--space-4); }
        .hero-title { font-size: var(--font-size-3xl); font-weight: 700; }

        /* ===== RESPONSIVIDADE ===== */
        @media (max-width: 768px) {
            .col-4, .col-6 { grid-column: span 12; }
            .nav-menu { position: fixed; top: 70px; left: -100%; flex-direction: column; background: white; width: 100%; transition: left var(--transition); padding: var(--space-4) 0; }
            .nav-menu.active { left: 0; }
            .hamburger { display: flex; }
            .hamburger.active span:nth-child(1) { transform: rotate(45deg) translate(5px, 5px); }
            .hamburger.active span:nth-child(2) { opacity: 0; }
            .hamburger.active span:nth-child(3) { transform: rotate(-45deg) translate(7px, -6px); }
            .hero-title { font-size: var(--font-size-2xl); }
        }

        @media (max-width: 480px) {
            .section { padding: var(--space-4) 0; }
            .hero { padding: var(--space-4) 0; }
            .hero-title { font-size: var(--font-size-xl); }
        }

        /* Utilitários */
        .text-center { text-align: center; }
        .d-flex { display: flex; }
        .gap-2 { gap: var(--space-2); }
        .w-100 { width: 100%; }
        .mt-3 { margin-top: var(--space-3); }
        .mb-3 { margin-bottom: var(--space-3); }
    </style>
</head>
<body>
    <!-- Navegação -->
    <nav class="navbar">
        <div class="container">
            <div class="nav-container">
                <a href="#" class="logo">DesignSystem</a>
                <ul class="nav-menu">
                    <li class="nav-item"><a href="#" class="nav-link">Início</a></li>
                    <li class="nav-item">
                        <a href="#" class="nav-link">Produtos <i class="fas fa-chevron-down"></i></a>
                        <div class="dropdown-menu">
                            <a href="#" class="dropdown-item">Web Design</a>
                            <a href="#" class="dropdown-item">UI/UX</a>
                        </div>
                    </li>
                    <li class="nav-item"><a href="#" class="nav-link">Contato</a></li>
                </ul>
                <div class="hamburger">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
        </div>
    </nav>

    <!-- Hero -->
    <section class="hero">
        <div class="container">
            <div class="hero-content">
                <h1 class="hero-title">Sistema de Design Profissional</h1>
                <p>Componentes responsivos e modernos para suas aplicações web</p>
                <div class="d-flex gap-2">
                    <a href="#" class="btn btn-secondary btn-lg">Começar Agora</a>
                    <a href="#" class="btn btn-outline btn-lg" style="color: white; border-color: white;">Saiba Mais</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Cards -->
    <section class="section">
        <div class="container">
            <div class="section-header">
                <h2 class="section-title">Nossos Projetos</h2>
                <p>Conheça nossos trabalhos mais recentes</p>
            </div>
            
            <div class="grid">
                <div class="col-4">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title">Sistema de Design</h3>
                            <p class="mb-3">Componentes reutilizáveis e documentação detalhada.</p>
                            <div class="d-flex justify-between align-center">
                                <span class="badge badge-primary">Em andamento</span>
                                <a href="#" class="btn btn-sm btn-primary">Ver Detalhes</a>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-4">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title">Dashboard Analytics</h3>
                            <p class="mb-3">Painel com gráficos interativos e relatórios.</p>
                            <div class="d-flex justify-between align-center">
                                <span class="badge badge-success">Concluído</span>
                                <a href="#" class="btn btn-sm btn-primary">Ver Detalhes</a>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-4">
                    <div class="card">
                        <div class="card-body">
                            <h3 class="card-title">App de Finanças</h3>
                            <p class="mb-3">Controle de gastos e planejamento financeiro.</p>
                            <div class="d-flex justify-between align-center">
                                <span class="badge badge-primary">Em andamento</span>
                                <a href="#" class="btn btn-sm btn-primary">Ver Detalhes</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Formulário e Alertas -->
    <section class="section" style="background-color: var(--neutral-100);">
        <div class="container">
            <div class="grid">
                <div class="col-6">
                    <h2 class="section-title">Entre em Contato</h2>
                    
                    <form>
                        <div class="form-group">
                            <label for="name" class="form-label">Nome</label>
                            <input type="text" id="name" class="form-control" placeholder="Seu nome">
                        </div>
                        
                        <div class="form-group">
                            <label for="email" class="form-label">E-mail</label>
                            <input type="email" id="email" class="form-control" placeholder="seu@email.com">
                        </div>
                        
                        <div class="form-group">
                            <label for="message" class="form-label">Mensagem</label>
                            <textarea id="message" class="form-control" rows="4" placeholder="Sua mensagem..."></textarea>
                        </div>
                        
                        <button type="submit" class="btn btn-primary w-100">Enviar Mensagem</button>
                    </form>
                </div>
                
                <div class="col-6">
                    <h2 class="section-title">Alertas</h2>
                    
                    <div class="alert alert-info">
                        <i class="fas fa-info-circle"></i> Esta é uma mensagem informativa
                    </div>
                    
                    <div class="alert alert-success">
                        <i class="fas fa-check-circle"></i> Operação concluída com sucesso!
                    </div>
                    
                    <button id="show-modal" class="btn btn-primary mt-3">Abrir Modal</button>
                </div>
            </div>
        </div>
    </section>

    <!-- Modal -->
    <div class="modal-overlay" id="modal-overlay">
        <div class="modal">
            <div class="modal-header">
                <h3>Modal de Exemplo</h3>
                <button class="modal-close" id="modal-close">
                    <i class="fas fa-times"></i>
                </button>
            </div>
            <div class="modal-body">
                <p>Conteúdo do modal aqui. Formulários, informações ou ações.</p>
            </div>
            <div class="modal-footer">
                <button class="btn btn-outline" id="modal-cancel">Cancelar</button>
                <button class="btn btn-primary" id="modal-confirm">Confirmar</button>
            </div>
        </div>
    </div>

    <script>
        // Menu Hamburguer
        const hamburger = document.querySelector('.hamburger');
        const navMenu = document.querySelector('.nav-menu');
        
        hamburger.addEventListener('click', () => {
            hamburger.classList.toggle('active');
            navMenu.classList.toggle('active');
        });

        // Modal
        const modalOverlay = document.getElementById('modal-overlay');
        const showModal = document.getElementById('show-modal');
        const modalClose = document.getElementById('modal-close');
        const modalCancel = document.getElementById('modal-cancel');

        [showModal, modalClose, modalCancel].forEach(element => {
            element.addEventListener('click', () => {
                modalOverlay.classList.toggle('active');
            });
        });
    </script>
</body>
</html>
