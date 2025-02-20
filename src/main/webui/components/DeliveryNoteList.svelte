<script>
  import { onMount } from "svelte";

  let deliveryNotes = [];
  let error = null;

  // REST-API-Endpunkt für Aufträge
  const API_URL = "http://localhost:8080/api/delivery-notes";

  // Daten beim Laden abrufen
  onMount(async () => {
    try {
      const response = await fetch(API_URL);
      if (!response.ok) throw new Error("Fehler beim Abrufen der Lieferscheine");
      deliveryNotes = await response.json();
    } catch (err) {
      error = err.message;
    }
  });

  // Auftrag löschen
  async function deleteDeliveryNote(deliveryNoteId) {
    try {
      const response = await fetch(`${API_URL}/${deliveryNoteId}`, { method: "DELETE" });
      if (!response.ok) throw new Error("Fehler beim Löschen des Lieferscheins");
      deliveryNotes = deliveryNotes.filter(deliveryNote => deliveryNote.deliveryNoteId !== deliveryNoteId);
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
      {#each deliveryNotes as deliveryNote}
        <tr>
          <td>{deliveryNote.deliveryNoteId}</td>
          <td>{deliveryNote.status}</td>
          <td>{deliveryNote.customerEmail}</td>
          <td>
            <button on:click={() => deleteDeliveryNote(deliveryNote.deliveryNoteId)}>Löschen</button>
          </td>
        </tr>
      {/each}
    </tbody>
  </table>
{/if}
