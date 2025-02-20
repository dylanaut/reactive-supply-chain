<script>
  import { onMount } from "svelte";

  let payments = [];
  let error = null;

  // REST-API-Endpunkt für Aufträge
  const API_URL = "http://localhost:8080/api/payments";

  // Daten beim Laden abrufen
  onMount(async () => {
    try {
      const response = await fetch(API_URL);
      if (!response.ok) throw new Error("Fehler beim Abrufen der Zahlungen");
      payments = await response.json();
    } catch (err) {
      error = err.message;
    }
  });

  // Auftrag löschen
  async function deletePayment(paymentId) {
    try {
      const response = await fetch(`${API_URL}/${paymentId}`, { method: "DELETE" });
      if (!response.ok) throw new Error("Fehler beim Löschen der Zahlung");
      payments = payments.filter(payment => payment.paymentId !== paymentId);
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
{#each payments as payment}
<tr>
  <td>{payment.paymentId}</td>
  <td>{payment.status}</td>
  <td>{payment.customerEmail}</td>
  <td>
    <button on:click={() => deletePayment(payment.paymentId)}>Löschen</button>
</td>
</tr>
{/each}
</tbody>
        </table>
        {/if}
