<script>
    import { wrap } from "svelte-spa-router/wrap";
    import Orders from "./pages/Orders.svelte";
    import Invoices from "./pages/Invoices.svelte";
    import Shipments from "./pages/Shipments.svelte";
    import DeliveryNotes from "./pages/DeliveryNotes.svelte";
    import Payments from "./pages/Payments.svelte";

    const routes = {
        "/orders": Orders,
        "/payments": Payments,
        "/invoices": Invoices,
        "/shipments": Shipments,
        "/delivery-notes": DeliveryNotes,
        "*": wrap({ component: Orders }), // Fallback zu Orders-Seite
    };
</script>

<Router {routes} />
