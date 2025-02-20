<script>
  import { onMount } from "svelte";

  let invoices = [];
  let error = null;

  // REST-API-Endpunkt für Aufträge
  const API_URL = "http://localhost:8080/api/invoices";

  // Daten beim Laden abrufen
  onMount(async () => {
    try {
      const response = await fetch(API_URL);
      if (!response.ok) throw new Error("Fehler beim Abrufen der Rechnungen");
      invoices = await response.json();
    } catch (err) {
      error = err.message;
    }
  });

  // Auftrag löschen
  async function deleteInvoice(invoiceId) {
    try {
      const response = await fetch(`${API_URL}/${invoiceId}`, { method: "DELETE" });
      if (!response.ok) throw new Error("Fehler beim Löschen der Rechnung");
      invoices = invoices.filter(invoice => invoice.invoiceId !== invoiceId);
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
      {#each invoices as invoice}
        <tr>
          <td>{invoice.invoiceId}</td>
          <td>{invoice.status}</td>
          <td>{invoice.customerEmail}</td>
          <td>
            <button on:click={() => deleteInvoice(invoice.invoiceId)}>Löschen</button>
          </td>
        </tr>
      {/each}
    </tbody>
  </table>
{/if}
