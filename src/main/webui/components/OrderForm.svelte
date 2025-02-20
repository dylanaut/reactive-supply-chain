<script>
  let customerEmail = "";
  let totalValue = "";

  const API_URL = "http://localhost:8080/api/orders";

  async function createOrder() {
    try {
      const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ customerEmail, totalValue }),
      });
      if (!response.ok) throw new Error("Fehler beim Erstellen des Auftrags");
      alert("Auftrag erfolgreich erstellt!");
      customerEmail = "";
      totalValue = "";
    } catch (err) {
      alert(err.message);
    }
  }
</script>

<h1>Neuen Auftrag erstellen</h1>

<form on:submit|preventDefault={createOrder}>
  <label for="email">Kunden-E-Mail:</label>
  <input id="email" bind:value={customerEmail} required />

  <label for="value">Gesamtwert:</label>
  <input id="value" type="number" bind:value={totalValue} required />

  <button type="submit">Erstellen</button>
</form>
