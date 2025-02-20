<script>
  import { onMount } from "svelte";

  let shipments = [];
  let error = null;

  // REST-API-Endpunkt für Aufträge
  const API_URL = "http://localhost:8080/api/shipments";

  // Daten beim Laden abrufen
  onMount(async () => {
    try {
      const response = await fetch(API_URL);
      if (!response.ok) throw new Error("Fehler beim Abrufen der Versanddaten");
      shipments = await response.json();
    } catch (err) {
      error = err.message;
    }
  });

  // Auftrag löschen
  async function deleteShipment(shipmentId) {
    try {
      const response = await fetch(`${API_URL}/${shipmentId}`, { method: "DELETE" });
      if (!response.ok) throw new Error("Fehler beim Löschen des Versand");
      shipments = shipments.filter(shipment => shipment.shipmentId !== shipmentId);
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
      {#each shipments as shipment}
        <tr>
          <td>{shipment.shipmentId}</td>
          <td>{shipment.status}</td>
          <td>{shipment.customerEmail}</td>
          <td>
            <button on:click={() => deleteShipment(shipment.shipmentId)}>Löschen</button>
          </td>
        </tr>
      {/each}
    </tbody>
  </table>
{/if}
