# EliteTintas 🎨📱

## 📌 Descrição do Projeto
EliteTintas é um aplicativo Android desenvolvido para oferecer atendimento personalizado aos clientes da EliteTintas. O app permite o cadastro e login de usuários e facilita a comunicação direta com atendentes especializados via WhatsApp.

## 🚀 Tecnologias Utilizadas
- **Linguagem:** Java ☕
- **IDE:** Android Studio 🏗️
- **Banco de Dados:** Firebase 🔥
- **Autenticação:** Firebase Authentication 🔐
- **Mensageria:** WhatsApp API 📩

## 📂 Estrutura do Projeto
```
EliteTintas/
│-- app/
│   ├── src/main/
│   │   ├── java/com/elitintastintas/app/
│   │   ├── res/
│   │   ├── AndroidManifest.xml
│   ├── build.gradle
│   ├── proguard-rules.pro
│
│-- .gitignore
│-- README.md
│-- build.gradle
│-- settings.gradle
```

## 📥 Instalação e Configuração

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/capf01/EliteTintas.git
   ```

2. **Abra o projeto no Android Studio**

3. **Configure o Firebase:**
   - Acesse [Firebase Console](https://console.firebase.google.com/)
   - Crie um novo projeto e adicione um app Android
   - Baixe o arquivo `google-services.json` e mova para `app/`
   - Ative Firebase Authentication e Firestore Database

4. **Sincronize as dependências:**
   ```bash
   ./gradlew build
   ```

5. **Execute o aplicativo em um emulador ou dispositivo físico**

## ⚙️ Funcionalidades
- 📌 Cadastro e login de usuários com Firebase Authentication
- 💬 Direcionamento automático para atendimento via WhatsApp
- 🔥 Integração com Firebase Firestore para armazenar dados dos usuários

## 🔧 Melhorias Futuras
- 📍 Implementação de um mapa interativo com localização das lojas
- 🎨 UI/UX aprimorada para melhor experiência do usuário
- 🛠️ Suporte a múltiplos idiomas

## 🤝 Contribuição
Fique à vontade para contribuir! Basta seguir os passos:
1. Faça um **fork** do repositório 🍴
2. Crie uma nova **branch** (`git checkout -b feature-minha-melhoria`)
3. Realize suas alterações e **commite** (`git commit -m 'Adiciona nova funcionalidade'`)
4. **Submeta um PR** para análise ✅

## 📄 Licença
Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.

---
Desenvolvido por **César Augusto Pacheco Ferreira** 🚀
