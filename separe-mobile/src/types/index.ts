export interface LoginResponse {
  token: string;
  matricula: string;
  nome: string;
  perfil: string;
}

export interface ColaboradorResponse {
  id: string;
  nome: string;
  email: string;
  matricula: string;
  perfil: string;
  ativo: boolean;
  dataCadastro: string;
}

export interface ItemPedido {
  nome: string;
  codigoBarras: string;
  quantidade: number;
  quantidadeBipada: number;
  bipagemCompleta: boolean;
}

export interface ClienteResponse {
  nome: string;
  cpfMascarado: string;
  telefone: string;
  email: string;
}

export interface PedidoResumo {
  id: string;
  numeroPedido: string;
  status: string;
  tipoEntrega: string;
  nomeCliente: string;
  dataRecebimento: string;
}

export interface PedidoDetalhe {
  id: string;
  numeroPedido: string;
  status: string;
  statusDescricao: string;
  tipoEntrega: string;
  tipoEntregaDescricao: string;
  cliente: ClienteResponse;
  itens: ItemPedido[];
  separadorMatricula: string;
  totalItens: number;
  totalItensBipados: number;
  todosItensBipados: boolean;
  dataRecebimento: string;
  dataInicioSeparacao: string;
  dataConclusaoSeparacao: string;
  dataEntrega: string;
}

export interface OcorrenciaResponse {
  id: string;
  pedidoId: string;
  produtoNome: string;
  codigoBarras: string;
  motivo: string;
  quantidadeNecessaria: number;
  quantidadeDisponivel: number;
  matriculaFuncionario: string;
  dataHora: string;
  observacao: string;
}

export interface ApiError {
  error: {
    code: string;
    message: string;
    details?: { field: string; issue: string }[];
    traceId: string;
  };
}
