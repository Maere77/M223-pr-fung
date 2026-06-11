// Auth Service - Dummy Implementation
export const authService = {
  registerUser: async (userData) => {
    // Simuliert API-Call mit Verzögerung
    return new Promise((resolve) => {
      setTimeout(() => {
        const newUser = {
          id: Math.random().toString(36).substr(2, 9),
          firstName: userData.firstName,
          lastName: userData.lastName,
          email: userData.email,
          createdAt: new Date().toISOString(),
        };
        console.log('User registriert:', newUser);
        resolve({
          success: true,
          message: 'Registrierung erfolgreich',
          user: newUser,
        });
      }, 1000);
    });
  },

  loginUser: async (username, passwordHash) => {
    const response = await fetch('http://localhost:8080/auth/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, passwordHash }),
    });

    if (!response.ok) {
      throw new Error(`HTTP ${response.status}`);
    }

    const data = await response.json();
    return { success: true, token: data.token };
  },

  validateEmail: (email) => {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
  },
};
