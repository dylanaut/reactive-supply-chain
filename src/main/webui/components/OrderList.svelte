<script>
  import { onMount } from "svelte";

  let orders = [];
  let error = null;

  // REST-API-Endpunkt für Aufträge
  const API_URL = "http://localhost:8080/api/orders";

  // Daten beim Laden abrufen
  onMount(async () => {
    try {
      const response = await fetch(API_URL);
      if (!response.ok) throw new Error("Fehler beim Abrufen der Aufträge");
      orders = await response.json();
    } catch (err) {
      error = err.message;
    }
  });

  // Auftrag löschen
  async function deleteOrder(orderId) {
    try {
      const response = await fetch(`${API_URL}/${orderId}`, { method: "DELETE" });
      if (!response.ok) throw new Error("Fehler beim Löschen des Auftrags");
      orders = orders.filter(order => order.orderId !== orderId);
    } catch (err) {
      alert(err.message);
    }
  }
</script>

<h1>Aufträge</h1>

{#if error}
  <p style="color: red">{error}</p>
{:else}
  <table>
    <thead>
      <tr>
        <th>Auftrags-ID</th>
        <th>Status</th>
        <th>Kunde</th>
        <th>Aktionen</th>
      </tr>
    </thead>
    <tbody>
      {#each orders as order}
        <tr>
          <td>{order.orderId}</td>
          <td>{order.status}</td>
          <td>{order.customerEmail}</td>
          <td>
            <button on:click={() => deleteOrder(order.orderId)}>Löschen</button>
          </td>
        </tr>
      {/each}
    </tbody>
  </table>
{/if}
