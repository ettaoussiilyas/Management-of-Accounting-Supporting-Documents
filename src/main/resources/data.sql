-- ===============================
-- COMPANIES (Sociétés)
-- ===============================
INSERT INTO company (id, sociale_raison, ice, address, phone_number, email)
VALUES
    (1, 'SARL AL AMANE CONSULTING', '001234567890123', '123 Avenue Mohammed V, Casablanca', '+212522123456', 'contact@alamanconsulting.ma'),
    (2, 'STE TECHNOLOGIES MAROC', '002345678901234', '45 Rue Ibn Batouta, Rabat', '+212537654321', 'info@techmaroc.ma'),
    (3, 'SARL DISTRIBUTION PLUS', '003456789012345', '78 Boulevard Hassan II, Marrakech', '+212524987654', 'contact@distributionplus.ma');

-- ===============================
-- USERS (Utilisateurs)
-- ===============================
-- COMPTABLE (Accountant)
INSERT INTO users (id, email, password, role, full_name, status, creation_date)
VALUES
    (1, 'comptable@alaman.ma', '$2a$10$N9qo8uLOickgx2ZMRZoMye.KdwjB1.EB6a2WcY4nHtGp4p2E2Wz7a', 'ACCOUNTANT', 'Ahmed Benali', 'ACTIVE', '2024-01-15');

-- SOCIETE USERS (Utilisateurs Société)
INSERT INTO users (id, email, password, role, full_name, status, creation_date, societe_id)
VALUES
    (2, 'karim@alamanconsulting.ma', '$2a$10$N9qo8uLOickgx2ZMRZoMye.KdwjB1.EB6a2WcY4nHtGp4p2E2Wz7a', 'SOCIETE', 'Karim El Fassi', 'ACTIVE', '2024-01-15', 1),
    (3, 'fatima@techmaroc.ma', '$2a$10$N9qo8uLOickgx2ZMRZoMye.KdwjB1.EB6a2WcY4nHtGp4p2E2Wz7a', 'SOCIETE', 'Fatima Zahra Alaoui', 'ACTIVE', '2024-01-16', 2),
    (4, 'mohamed@distributionplus.ma', '$2a$10$N9qo8uLOickgx2ZMRZoMye.KdwjB1.EB6a2WcY4nHtGp4p2E2Wz7a', 'SOCIETE', 'Mohamed Amine Berrada', 'ACTIVE', '2024-01-17', 3);

-- ===============================
-- DOCUMENTS (Documents)
-- ===============================
-- Documents pour AL AMANE CONSULTING
INSERT INTO document (id, document_number, type, category, document_date, cost, supplier, document, status, comment, creation_date, modification_date, company_id)
VALUES
    (1, 'FAC-2024-001', 'PurchaseInvoice', 'FOURNITURES', '2024-01-10', 15000.00, 'SARL PAPETERIE MODERNE', '/documents/fac-001.pdf', 'VALIDATED', 'Facture validée - Fournitures bureau', '2024-01-10', '2024-01-11', 1),
    (2, 'FAC-2024-002', 'PurchaseInvoice', 'LOGICIEL', '2024-01-12', 45000.00, 'MICROSOFT MAROC', '/documents/fac-002.pdf', 'PENDING', 'En attente validation comptable', '2024-01-12', '2024-01-12', 1),
    (3, 'VENTE-2024-001', 'SalesInvoice', 'CONSULTING', '2024-01-15', 75000.00, 'CLIENT XYZ', '/documents/vente-001.pdf', 'VALIDATED', 'Facture client validée', '2024-01-15', '2024-01-16', 1);

-- Documents pour TECHNOLOGIES MAROC
INSERT INTO document (id, document_number, type, category, document_date, cost, supplier, document, status, comment, creation_date, modification_date, company_id)
VALUES
    (4, 'TM-2024-001', 'PurchaseInvoice', 'MATERIEL_INFORMATIQUE', '2024-01-08', 120000.00, 'DELL MAROC', '/documents/tm-001.pdf', 'VALIDATED', 'Achat ordinateurs serveur', '2024-01-08', '2024-01-09', 2),
    (5, 'TM-2024-002', 'BankStatement', 'OPERATIONS_BANCAIRES', '2024-01-20', NULL, 'ATTIJARIWAFA BANK', '/documents/tm-002.pdf', 'PENDING', 'Relevé bancaire janvier', '2024-01-20', '2024-01-20', 2),
    (6, 'TM-2024-003', 'Receipt', 'FRAIS_DEPLACEMENT', '2024-01-25', 2500.00, 'TAXI VILLE', '/documents/tm-003.pdf', 'REJECTED', 'Note de frais rejetée - Justificatif manquant', '2024-01-25', '2024-01-26', 2);

-- Documents pour DISTRIBUTION PLUS
INSERT INTO document (id, document_number, type, category, document_date, cost, supplier, document, status, comment, creation_date, modification_date, company_id)
VALUES
    (7, 'DP-2024-001', 'PurchaseInvoice', 'STOCK', '2024-01-05', 85000.00, 'GROSSISTA SARL', '/documents/dp-001.pdf', 'VALIDATED', 'Achat stock produits', '2024-01-05', '2024-01-06', 3),
    (8, 'DP-2024-002', 'SalesInvoice', 'VENTES', '2024-01-18', 45000.00, 'SUPERMARCHE ALIMENT', '/documents/dp-002.pdf', 'PENDING', 'Facture vente en attente', '2024-01-18', '2024-01-18', 3),
    (9, 'DP-2024-003', 'Receipt', 'ENTRETIEN', '2024-01-22', 3200.00, 'GARAGE AUTO PLUS', '/documents/dp-003.pdf', 'VALIDATED', 'Entretien véhicule de livraison', '2024-01-22', '2024-01-23', 3);